package com.ban2apps.me.database

import android.content.Context
import android.util.Log
import com.ban2apps.me.database.data.Story
import com.ban2apps.me.utils.Prefs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import java.util.concurrent.Executor
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class Repository private constructor(private val database: MeDatabase,
                                     private val executor: Executor) {

    companion object {

        private val LOG_TAG = Repository::class.java.simpleName

        // Firebase instance variables
        private val firebaseDatabase = FirebaseDatabase.getInstance()
        private var userId: String = "anonymous"

        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: Repository? = null

        @Synchronized
        fun getInstance(
                database: MeDatabase, executor: Executor, context: Context): Repository {
            if (Prefs.getId(context) != null) {
                userId = Prefs.getId(context)!!
            }
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

    fun syncFromServer() {
        firebaseDatabase.getReference(userId).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                for (child: DataSnapshot in snapshot.children) {
                    val story = child.getValue(Story::class.java)!!
                    executor.execute { database.storyDao().insert(story) }
                }
            }
        })
    }

    fun getStory(id: Int) = database.storyDao().getStory(id)
    val stories = database.storyDao().getStories()
    fun insertStory(story: Story) {
        executor.execute { database.storyDao().insert(story) }
        firebaseDatabase.reference.child(userId).child(UUID.randomUUID().toString()).setValue(story)
    }
    fun deleteStory(id: Int) = executor.execute { database.storyDao().delete(id) }
    fun deleteAll() = executor.execute { database.storyDao().deleteAll() }
}