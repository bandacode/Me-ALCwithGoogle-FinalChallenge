package com.ban2apps.me.database.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stories")
data class Story(@PrimaryKey(autoGenerate = true) var id: Int?,
                 var title: String,
                 var story: String,
                 var timestamp: Long) {
    @Ignore constructor():this(null, "", "", 0)
}