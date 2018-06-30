package com.ban2apps.me.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ban2apps.me.database.data.Story

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories WHERE id = :id")
    fun getStory(id: Int): LiveData<Story>

    @Query("SELECT * FROM stories ORDER BY timestamp DESC")
    fun getStories(): LiveData<List<Story>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(story: Story)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stories: List<Story>)

    @Query("DELETE FROM stories WHERE id = :id")
    fun delete(id: Int)

    @Query("DELETE FROM stories")
    fun deleteAll()
}