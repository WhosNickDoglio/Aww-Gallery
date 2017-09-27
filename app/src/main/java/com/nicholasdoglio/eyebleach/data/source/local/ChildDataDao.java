package com.nicholasdoglio.eyebleach.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.nicholasdoglio.eyebleach.data.model.ChildData;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ChildDataDao {

    @Query("SELECT * FROM ChildData")
    Flowable<List<ChildData>> getAllPosts();

    @Query("SELECT * FROM ChildData")
    LiveData<List<ChildData>> getPostsLive();

    @Query("SELECT * FROM ChildData")
    LivePagedListProvider<Integer, ChildData> getPosts();

    @Query("SELECT COUNT(*) FROM ChildData")
    int getNumberofPosts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertChildDataList(List<ChildData> childData);
}