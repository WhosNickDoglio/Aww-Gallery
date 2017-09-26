package com.nicholasdoglio.eyebleach.data.source.local;

import android.arch.paging.LivePagedListProvider;

import com.nicholasdoglio.eyebleach.data.model.ChildData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class RedditPostLocalDataSource {
    private final RedditPostDatabase redditPostDatabase;

    @Inject
    RedditPostLocalDataSource(RedditPostDatabase database) {
        redditPostDatabase = database;
    }

    public void savePostsToDb(List<ChildData> childDataList) {
        new Thread(() -> redditPostDatabase.childDataDao().insertChildDataList(childDataList)).start();
    }

    public Flowable<List<ChildData>> getPostsFlow() {
        return redditPostDatabase.childDataDao().getAllPosts();
    }

    public Boolean databaseIsEmpty() {
        final int[] databaseCount = new int[1];

        new Thread(() -> databaseCount[0] = redditPostDatabase.childDataDao().getNumberofPosts()).start();

        return databaseCount[0] <= 0;
    }

    public String getLastPostId() {
        final String[] id = new String[1];
        new Thread(() -> id[0] = redditPostDatabase.childDataDao().getLastItemId());

        return id[0];
    }

    public LivePagedListProvider<Integer, ChildData> PagedList() {
        return redditPostDatabase.childDataDao().getPosts();
    }
}
