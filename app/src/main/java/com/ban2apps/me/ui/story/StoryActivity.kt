package com.ban2apps.me.ui.story

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.ban2apps.me.R
import com.ban2apps.me.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : AppCompatActivity() {

    private var isEdit = false
    private lateinit var viewModel: StoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        val factory = InjectorUtils.provideStoryViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory)[StoryViewModel::class.java]

        isEdit = intent.getBooleanExtra("edit", false)
        if (isEdit) {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        } else {
            val id = intent.extras["id"] as Int
            viewModel.getStory(id).observe(this, Observer {
                if (it != null) {
                    titleEditText.setText(it.title, TextView.BufferType.EDITABLE)
                    storyEditText.setText(it.title, TextView.BufferType.EDITABLE)
                    titleEditText.isEnabled = false
                    storyEditText.isEnabled = false
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_edit_story, menu)
        } else {
            menuInflater.inflate(R.menu.menu_read_story, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                isEdit = true
                titleEditText.isEnabled = true
                storyEditText.isEnabled = true
                invalidateOptionsMenu()
                true
            }
            R.id.action_delete -> true
            R.id.action_save -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (isEdit) {
            titleEditText.isEnabled = false
            storyEditText.isEnabled = false
            invalidateOptionsMenu()
        } else {
            super.onBackPressed()
        }
    }
}
