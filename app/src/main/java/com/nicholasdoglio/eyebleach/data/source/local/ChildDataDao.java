package com.nicholasdoglio.eyebleach.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nicholasdoglio.eyebleach.data.model.ChildData;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ChildDataDao {

    @Query("SELECT * FROM ChildData")
    Flowable<List<ChildData>> getAllPosts();

    @Query("SELECT thumbnail FROM ChildData")
    List<String> getAllThumbnailUrls();

    @Query("SELECT * FROM ChildData limit (:limit)")
    List<ChildData> getPostsForSwipe(int limit);

    @Query("SELECT url FROM ChildData ")
    List<String> getAllImageUrl();


    @Query("SELECT url FROM ChildData WHERE id = (:id)")
    String getImageById(String id);


    @Query("SELECT id FROM ChildData WHERE thumbnail = (:thumbnail)")
    String getIdbythumbnailUrl(String thumbnail);

    @Query("SELECT COUNT(*) FROM ChildData")
    int getNumberofPosts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChildDataList(List<ChildData> childData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChildData(ChildData childData);

    @Update
    void updateChildData(List<ChildData> childData);

    @Delete
    void deleteChildData(ChildData childData);

    @Delete
    void deleteAllData(List<ChildData> childData);

    @Update
    void updateData(List<ChildData> childData);
}
