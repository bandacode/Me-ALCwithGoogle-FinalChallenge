package com.ban2apps.me.ui.story

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ban2apps.me.R

class StoryActivity : AppCompatActivity() {

    private var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        isEdit = intent.getBooleanExtra("edit", false)
        if (isEdit) {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
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
            R.id.action_edit -> true
            R.id.action_delete -> true
            R.id.action_save -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
