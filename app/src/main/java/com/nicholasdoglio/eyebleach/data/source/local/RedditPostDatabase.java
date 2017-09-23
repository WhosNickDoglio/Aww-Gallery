package com.nicholasdoglio.eyebleach.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nicholasdoglio.eyebleach.data.model.ChildData;

@Database(entities = {ChildData.class}, version = 1)
public abstract class RedditPostDatabase extends RoomDatabase {
    public abstract ChildDataDao childDataDao();
}

//TODO Work on migration method
