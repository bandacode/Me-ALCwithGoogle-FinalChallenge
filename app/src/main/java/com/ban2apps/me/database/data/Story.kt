package com.ban2apps.me.database.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stories")
data class Story(@PrimaryKey(autoGenerate = true) val id: Int?,
                 val title: String,
                 val story: String,
                 val timestamp: Long)