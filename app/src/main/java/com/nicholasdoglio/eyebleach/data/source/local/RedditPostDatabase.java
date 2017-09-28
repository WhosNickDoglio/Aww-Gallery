package com.nicholasdoglio.eyebleach.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nicholasdoglio.eyebleach.data.model.ChildData;

//TODO Work on migration method
@Database(entities = {ChildData.class}, version = 1)
public abstract class RedditPostDatabase extends RoomDatabase {
    public abstract ChildDataDao childDataDao();
}