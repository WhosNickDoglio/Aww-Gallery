package com.nicholasdoglio.eyebleach.data.source;

import android.arch.paging.LivePagedListProvider;

import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.data.model.Multireddit;
import com.nicholasdoglio.eyebleach.data.source.local.RedditPostDatabase;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class RedditPostRepository {
    private final RedditPostDatabase postDatabase;

    @Inject
    RedditService redditService;

    private List<ChildData> posts = new ArrayList<>();
    private List<ChildData> loadMoreList = new ArrayList<>();

    @Inject
    public RedditPostRepository(RedditPostDatabase postDatabase) {
        this.postDatabase = postDatabase;
    }

    public Flowable<List<ChildData>> getPosts(int limit) {
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
//            return null;
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