package com.ban2apps.me.ui.story

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ban2apps.me.R
import com.ban2apps.me.database.data.Story
import com.ban2apps.me.utils.InjectorUtils

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val factory = InjectorUtils.provideStoryViewModelFactory(this)
        val viewModel = ViewModelProviders.of(this, factory)[StoryViewModel::class.java]
        emptyJournal.visibility = View.GONE

        entriesRecycler.layoutManager = LinearLayoutManager(this)
        entriesRecycler.adapter = StoryAdapter(getStories()) {
            val intent = Intent(this@MainActivity, StoryActivity::class.java)
            intent.putExtra("edit", false)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, StoryActivity::class.java)
            intent.putExtra("edit", true)
            startActivity(intent)
        }
    }

    private fun getStories(): List<Story> {
        val stories = ArrayList<Story>()
        stories.add(Story(1,"Hi there", "How are you?", System.currentTimeMillis() - 100000))
        stories.add(Story(2,"Good morning", "Top of the morning to you", System.currentTimeMillis() - 11000000))
        stories.add(Story(3,"Afternoon", "Only after the morning", System.currentTimeMillis() - 36500000))
        stories.add(Story(4,"Evening", "How was your day?", System.currentTimeMillis() - 150000000))
        stories.add(Story(5,"Night", "How was your day?", System.currentTimeMillis() - 180000000))
        stories.add(Story(6,"Whats up", "How was your day?", System.currentTimeMillis() - 230000000))
        stories.add(Story(7,"Good night", "Don't let the bed bugs bite!", System.currentTimeMillis() - 450000000))
        return stories
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
