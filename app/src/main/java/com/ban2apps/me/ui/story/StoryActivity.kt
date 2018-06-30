package com.ban2apps.me.ui.story

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.ban2apps.me.R
import com.ban2apps.me.database.data.Story
import com.ban2apps.me.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : AppCompatActivity() {

    private var isEdit = false
    private lateinit var viewModel: StoryViewModel
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = InjectorUtils.provideStoryViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)[StoryViewModel::class.java]

        isEdit = intent.getBooleanExtra("edit", false)
        if (isEdit) {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        } else {
            id = intent.extras["id"] as Int
            viewModel.getStory(id!!).observe(this, Observer {
                if (it != null) {
                    titleTextView.text = it.title
                    storyTextView.text = it.story
                    showViews()
                }
            })
        }
    }

    private fun showViews() {
        if (isEdit) {
            titleEditText.visibility = View.VISIBLE
            storyEditText.visibility = View.VISIBLE
            val title = titleTextView.text
            val story = storyTextView.text
            titleEditText.setText(title, TextView.BufferType.EDITABLE)
            storyEditText.setText(story, TextView.BufferType.EDITABLE)
            titleTextView.visibility = View.GONE
            storyTextView.visibility = View.GONE
        } else {
            titleEditText.visibility = View.GONE
            storyEditText.visibility = View.GONE
            titleTextView.visibility = View.VISIBLE
            storyTextView.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_edit_story, menu)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        } else {
            menuInflater.inflate(R.menu.menu_read_story, menu)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                isEdit = true
                showViews()
                invalidateOptionsMenu()
                true
            }
            R.id.action_delete -> {
                viewModel.deleteStory(id!!)
                finish()
                true
            }
            R.id.action_save -> {
                val title = titleEditText.text.toString()
                val story = storyEditText.text.toString()
                viewModel.insertStory(Story(id, title, story, System.currentTimeMillis()))
                finish()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (isEdit) {
            isEdit = false
            showViews()
            invalidateOptionsMenu()
        } else {
            super.onBackPressed()
        }
    }
}
