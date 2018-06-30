package com.ban2apps.me.ui.launch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import android.util.Log
import android.widget.Toast
import com.ban2apps.me.R
import com.ban2apps.me.ui.story.MainActivity
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private val RC_SIGN_IN = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            val intent = Intent(this@LaunchActivity, MainActivity::class.java)
            startActivity(intent)
        }

        skipText.setOnClickListener {
            val intent = Intent(this@LaunchActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Sign in Successful", Toast.LENGTH_SHORT).show()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("LauncherActivity", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(this, "Sign in Failed", Toast.LENGTH_SHORT).show()
        }

    }
}
