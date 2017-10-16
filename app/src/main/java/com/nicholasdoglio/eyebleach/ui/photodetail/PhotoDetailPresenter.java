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
package com.nicholasdoglio.eyebleach.ui.photodetail;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.data.source.RedditPostRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author Nicholas Doglio
 */
public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {
    private static final int IMAGES_LOAD_PHOTODETAIL = 24;
    private final LiveData<PagedList<ChildData>> photoDetailList;
    private PhotoDetailContract.View photoDetailView;
    private RedditPostRepository repository;
    private CompositeDisposable compositeDisposable;


    @Inject
    PhotoDetailPresenter(RedditPostRepository redditPostRepository) {
        repository = redditPostRepository;
        compositeDisposable = new CompositeDisposable();
        photoDetailList = repository.getPagedList().create(0,
                new PagedList.Config.Builder()
                        .setPageSize(1)
                        .setPrefetchDistance(5)
                        .setEnablePlaceholders(true)
                        .build());
    }

    public void load() {
        compositeDisposable.add(repository.getPostsFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<ChildData>>() {
                    @Override
                    public void onNext(List<ChildData> childData) {
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    void loadMore() {
        compositeDisposable.add(repository.getMorePosts(IMAGES_LOAD_PHOTODETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ChildData>>() {
                    @Override
                    public void onSuccess(List<ChildData> childData) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }


    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    public void takeView(PhotoDetailContract.View view) {
        this.photoDetailView = view;
    }

    @Override
    public void dropView() {
        photoDetailView = null;
    }

    LiveData<PagedList<ChildData>> getPhotoDetailList() {
        return photoDetailList;
    }
}