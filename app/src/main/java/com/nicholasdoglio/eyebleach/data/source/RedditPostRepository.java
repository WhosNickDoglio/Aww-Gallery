package com.nicholasdoglio.eyebleach.data.source;

import android.arch.paging.LivePagedListProvider;

import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.data.model.Multireddit;
import com.nicholasdoglio.eyebleach.data.source.local.RedditPostLocalDataSource;
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
    private final RedditPostLocalDataSource localSource;
    @Inject
    RedditService redditService;
    private List<ChildData> firstLoadList;
    private List<ChildData> loadMoreList;

    @Inject
    RedditPostRepository(RedditPostLocalDataSource redditPostLocalDataSource) {
        localSource = redditPostLocalDataSource;
    }

    public Flowable<List<ChildData>> getPosts(int limit) {
        firstLoadList = new ArrayList<>();
        if (localSource.databaseIsEmpty()) {
            return redditService.getMultiPosts(limit, "")
                    .map(multireddit -> {
                        filterForImages(multireddit, firstLoadList);
                        return firstLoadList;
                    }).doOnEach(new Subscriber<List<ChildData>>() {
                        @Override
                        public void onSubscribe(Subscription s) {

                        }

                        @Override
                        public void onNext(List<ChildData> childData) {
                            localSource.savePostsToDb(childData);

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
            return localSource.getPostsFlow();
        }
    }

    public Flowable<List<ChildData>> getMorePosts(int limit) {//Can/should I combine this with getPosts()?
        loadMoreList = new ArrayList<>();
        return redditService.getMultiPosts(limit, "t3_" + localSource.getLastPostId())
                .map(multireddit -> {
                    filterForImages(multireddit, loadMoreList);
                    return loadMoreList;
                }).doOnEach(new Subscriber<List<ChildData>>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(List<ChildData> childData) {
                        localSource.savePostsToDb(childData);

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
        return localSource.PagedList();
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