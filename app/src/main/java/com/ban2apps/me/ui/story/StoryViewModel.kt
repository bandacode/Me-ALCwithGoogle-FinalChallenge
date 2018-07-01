package com.ban2apps.me.ui.story

import android.arch.lifecycle.ViewModel
import com.ban2apps.me.database.Repository
import com.ban2apps.me.database.data.Story

class StoryViewModel(private val repository: Repository) : ViewModel() {
    fun getStory(id: Int) = repository.getStory(id)
    val stories = repository.stories
    fun insertStory(story: Story) = repository.insertStory(story)
    fun deleteStory(id: Int) = repository.deleteStory(id)
    fun deleteAll() = repository.deleteAll()
    fun sync() = repository.syncFromServer()
}