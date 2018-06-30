package com.ban2apps.me.database

import android.util.Log
import com.ban2apps.me.database.data.Story
import java.util.concurrent.Executor

class Repository private constructor(private val database: MeDatabase,
                                     private val executor: Executor) {

    companion object {

        private val LOG_TAG = Repository::class.java.simpleName

        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: Repository? = null

        @Synchronized
        fun getInstance(
                database: MeDatabase, executor: Executor): Repository {
            Log.d(LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Repository(database, executor)
                    Log.d(LOG_TAG, "Made new repository")
                }
            }
            return sInstance!!
        }
    }

    fun getStory(id: Int) = database.storyDao().getStory(id)
    val stories = database.storyDao().getStories()
    fun insertStory(story: Story) = executor.execute { database.storyDao().insert(story) }
    fun insertAll(stories: List<Story>) = executor.execute { database.storyDao().insertAll(stories) }
    fun deleteStory(id: Int) = executor.execute { database.storyDao().delete(id) }
    fun deleteAll() = executor.execute { database.storyDao().deleteAll() }
}