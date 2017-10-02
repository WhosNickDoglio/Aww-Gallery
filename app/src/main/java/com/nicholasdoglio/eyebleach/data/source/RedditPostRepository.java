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

import android.arch.paging.LivePagedListProvider;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.source.local.RedditPostDatabase;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * @author Nicholas Doglio
 */
@Singleton
public class RedditPostRepository {
    private final RedditPostDatabase postDatabase;

    @Inject
    RedditService redditService;

    private List<ChildData> posts;
    private List<ChildData> loadMoreList;

    @Inject
    RedditPostRepository(RedditPostDatabase postDatabase) {
        this.postDatabase = postDatabase;
        posts = new ArrayList<>();
        loadMoreList = new ArrayList<>();
    }

    public Flowable<List<ChildData>> getFirstLoadPosts(int limit) {//No network connection crashes the app
        posts.clear();

        if (databaseIsEmpty()) {
            return redditService.getMultiPosts(limit, "")
                    .map(multireddit -> {
                        filterForImages(multireddit, posts);
                        return posts;
                    }).doOnEach(new Subscriber<List<ChildData>>() {
                        @Override
                        public void onSubscribe(Subscription s) {

                        }

                        @Override
                        public void onNext(List<ChildData> childData) {
                            postDatabase.beginTransaction();
                            try {
                                postDatabase.childDataDao().deleteAll();
                                postDatabase.childDataDao().insertChildDataList(childData);
                                postDatabase.setTransactionSuccessful();
                            } finally {
                                postDatabase.endTransaction();
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            t.getMessage();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else {
            return postDatabase.childDataDao().getAllPosts();
        }
    }

    public Flowable<List<ChildData>> getMorePosts(int limit) {
        return redditService.getMultiPosts(limit, "t3_" + posts.get(posts.size() - 1).getId())
                .map(multireddit -> {
                    filterForImages(multireddit, loadMoreList);
                    return loadMoreList;
                }).doOnEach(new Subscriber<List<ChildData>>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(List<ChildData> childData) {
                        postDatabase.beginTransaction();
                        try {
                            postDatabase.childDataDao().insertChildDataList(childData);
                            postDatabase.setTransactionSuccessful();

                        } finally {
                            postDatabase.endTransaction();
                        }
                        posts.addAll(childData);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.getMessage();
                    }

                    @Override
                    public void onComplete() {
                        loadMoreList.clear();
                    }
                });
    }

    public LivePagedListProvider<Integer, ChildData> pagedList() {
        return postDatabase.childDataDao().getPosts();
    }

    private Boolean databaseIsEmpty() {
        final int[] databaseCount = new int[1];

        new Thread(() -> databaseCount[0] = postDatabase.childDataDao().getNumberofPosts()).start();

        return databaseCount[0] <= 0;
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