package com.nicholasdoglio.eyebleach.data.source;

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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class RedditPostRepository {
    private final RedditPostLocalDataSource localSource;

    @Inject
    RedditService redditService;

    @Inject
    RedditPostRepository(RedditPostLocalDataSource redditPostLocalDataSource) {
        localSource = redditPostLocalDataSource;
    }

    public Flowable<Multireddit> getInitialLoad(int limit) {
        return redditService.getMultiPosts(limit, "");
    }

    public void SwipeLoad(int limit) {
        List<ChildData> swpeChildData = new ArrayList<>();

        /*
        Initial load will pull from network if DB is empty, if DB is full will check for differences and update accordingly
        ONLY USED FOR SWIPE
         */
        if (localSource.databaseIsEmpty()) {
            getInitialLoad(limit)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Multireddit>() {
                        @Override
                        public void onSubscribe(Subscription s) {

                        }

                        @Override
                        public void onNext(Multireddit multireddit) {
                            filterForImages(multireddit, swpeChildData);
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            localSource.savePostsToDb(swpeChildData);
        } else {

            //check for conflicts and update DB
        }
    }

    public void loadMore() {
        /*
        Need to figure out how to use this with the Paging Library, can I call it at a location in the RecyclerView?
         */
    }


    public List<ChildData> getPostsRemote(int limit) {
        List<ChildData> childData = new ArrayList<>();

        redditService.getMultiPosts(limit, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(multireddit -> filterForImages(multireddit, childData));

        return childData;
    }


    public Flowable<List<ChildData>> getPosts(int limit) {
        if (localSource.databaseIsEmpty()) {

        } else {

        }


        return null;
    }

    private void filterForImages(Multireddit networkMulti, List<ChildData> childDataList) {
        for (int i = 0; i < networkMulti.getData().getChildren().size(); i++) {
//            if (networkMulti.getData().getChildren().get(i).getChildData().getUrl().contains(".jpg") ||
//                    networkMulti.getData().getChildren().get(i).getChildData().getUrl().contains(".png")) {
            childDataList.add(networkMulti.getData().getChildren().get(i).getChildData());
        }
//        }
    }

    private void saveToDb(List<ChildData> childData) {
        localSource.savePostsToDb(childData);
    }

}
