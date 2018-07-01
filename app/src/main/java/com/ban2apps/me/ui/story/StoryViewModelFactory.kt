package com.ban2apps.me.ui.story

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ban2apps.me.database.Repository

@Suppress("UNCHECKED_CAST")
class StoryViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = StoryViewModel(repository) as T
}