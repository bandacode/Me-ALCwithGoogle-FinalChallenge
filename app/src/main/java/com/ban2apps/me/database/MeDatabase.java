package com.ban2apps.me.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ban2apps.me.database.dao.StoryDao;
import com.ban2apps.me.database.data.Story;

@Database(entities = {Story.class}, version = 1, exportSchema = false)
public abstract class MeDatabase extends RoomDatabase {

    public abstract StoryDao storyDao();

    private static final String DATABASE_NAME = "me_database";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile MeDatabase sInstance;

    public static MeDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MeDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}
