/*
    Aww Gallery
    Copyright (C) 2017  Nicholas Doglio

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nicholasdoglio.eyebleach.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author Nicholas Doglio
 */
@Dao
public interface ChildDataDao {

    @Query("SELECT * FROM ChildData")
    Flowable<List<ChildData>> getAllPosts();

    @Query("SELECT * FROM ChildData ")
    Flowable<ChildData> getPostsForPosition();

    @Query("SELECT * FROM ChildData")
    LiveData<List<ChildData>> getPostsLive();

    @Query("SELECT thumbnail FROM ChildData")
    LiveData<List<String>> getThumbnails(); //Maybe do it like this instead?

    @Query("SELECT * FROM ChildData")
    LivePagedListProvider<Integer, ChildData> getPosts();

    @Query("DELETE FROM ChildData")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertChildDataList(List<ChildData> childData);
}