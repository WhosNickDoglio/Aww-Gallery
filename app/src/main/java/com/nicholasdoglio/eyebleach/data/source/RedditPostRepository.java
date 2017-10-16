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
package com.nicholasdoglio.eyebleach.data.source;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListProvider;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.source.local.RedditPostDatabase;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * @author Nicholas Doglio
 */
@Singleton
public class RedditPostRepository {
    private static final String TAG = RedditPostRepository.class.getSimpleName();
    private final RedditPostDatabase postDatabase;
    @Inject
    RedditAPI redditAPI;

    private List<ChildData> posts;
    private List<ChildData> loadMoreList;

    @Inject
    RedditPostRepository(RedditPostDatabase postDatabase) {
        this.postDatabase = postDatabase;
        posts = new ArrayList<>();
        loadMoreList = new ArrayList<>();
    }

    public Single<List<ChildData>> getPostsFirstLoad(int limit) {
        posts.clear();
        return redditAPI.getMultiPosts(limit, "")
                .map(multireddit -> {
                    filterForImages(multireddit, posts);
                    return posts;
                }).doOnSuccess(childData -> {
                    postDatabase.runInTransaction(() -> {
                        postDatabase.childDataDao().deleteAll();
                        postDatabase.childDataDao().insertChildDataList(childData);
                    });
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        // TODO: FIGURE OUT HOW TO PROPERLY HANDLE RXERRORS
                    }
                });
    }

    public Single<List<ChildData>> getMorePosts(int limit) {
        return redditAPI.getMultiPosts(limit, "t3_" + posts.get(posts.size() - 1).getId())
                .map(multireddit -> {
                    filterForImages(multireddit, loadMoreList);
                    return loadMoreList;
                })
                .doOnSuccess(childData -> {
                    postDatabase.beginTransaction();
                    try {
                        postDatabase.childDataDao().insertChildDataList(childData);
                        postDatabase.setTransactionSuccessful();

                    } finally {
                        postDatabase.endTransaction();
                    }
                    posts.addAll(childData);
                    loadMoreList.clear();
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    public LivePagedListProvider<Integer, ChildData> getPagedList() {
        return postDatabase.childDataDao().getPagedList();
    }

    public Flowable<List<ChildData>> getPostsFromDb() {
        return postDatabase.childDataDao().getPosts();
    }

    public LiveData<List<ChildData>> getPostsLive() {
        return postDatabase.childDataDao().getPostsLive();
    }

    public Completable getCount() {
        return Completable.fromAction(() -> postDatabase.childDataDao().getCount());
    }

    private void filterForImages(Multireddit networkMulti, List<ChildData> childDataList) {
        /*
        Will eventually change this when I add support for gifs, imgur albums, youtube links, and reddit videos/images
        Will only need to filter out text posts
         */
        for (int i = 0; i < networkMulti.getData().getChildren().size(); i++) {
            if (networkMulti.getData().getChildren().get(i).getChildData().getUrl().contains(".jpg") ||
                    networkMulti.getData().getChildren().get(i).getChildData().getUrl().contains(".png")) {
                childDataList.add(networkMulti.getData().getChildren().get(i).getChildData());
            }
        }
    }
}