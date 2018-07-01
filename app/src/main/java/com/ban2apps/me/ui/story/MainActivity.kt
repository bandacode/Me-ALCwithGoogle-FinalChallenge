package com.ban2apps.me.ui.story

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ban2apps.me.R
import com.ban2apps.me.ui.launch.SignInActivity
import com.ban2apps.me.utils.InjectorUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: StoryViewModel

    // Firebase instance variables
    private var mFirebaseAuth: FirebaseAuth? = null
    private var mFirebaseUser: FirebaseUser? = null
    private var mUsername: String? = ""
    private var mPhotoUrl: String? = ""
    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mFirebaseAuth!!.currentUser

        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        } else {
            mUsername = mFirebaseUser!!.displayName
            if (mFirebaseUser!!.photoUrl != null) {
                mPhotoUrl = mFirebaseUser!!.photoUrl.toString()
            }
        }

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, null)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build()

        val factory = InjectorUtils.provideStoryViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)[StoryViewModel::class.java]
        viewModel.stories.observe(this, Observer {
            if (it != null) {
                if (!it.isEmpty()) emptyJournal.visibility = View.GONE
                entriesRecycler.layoutManager = LinearLayoutManager(this)
                entriesRecycler.adapter = StoryAdapter(it) { story ->
                    val intent = Intent(this@MainActivity, StoryActivity::class.java)
                    intent.putExtra("edit", false)
                    intent.putExtra("id", story.id)
                    startActivity(intent)
                }
            } else emptyJournal.visibility = View.VISIBLE
        })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, StoryActivity::class.java)
            intent.putExtra("edit", true)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                mFirebaseAuth!!.signOut()
                Auth.GoogleSignInApi.signOut(mGoogleApiClient)
                mUsername = "anonymous"
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
                true
            }
            R.id.action_delete_all -> {
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
