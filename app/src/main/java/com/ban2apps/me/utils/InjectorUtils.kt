package com.ban2apps.me.utils

import android.content.Context
import com.ban2apps.me.database.MeDatabase
import com.ban2apps.me.database.Repository
import com.ban2apps.me.ui.story.StoryViewModelFactory

object InjectorUtils {

    private fun provideRepository(context: Context): Repository {
        val database = MeDatabase.getInstance(context.applicationContext)
        val executor = AppExecutor.getInstance()
        return Repository.getInstance(database, executor, context)
    }

    fun provideStoryViewModelFactory(context: Context): StoryViewModelFactory {
        val repository = provideRepository(context.applicationContext)
        return StoryViewModelFactory(repository)
    }
}