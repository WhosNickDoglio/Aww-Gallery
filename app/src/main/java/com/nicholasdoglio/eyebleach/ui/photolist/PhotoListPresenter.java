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
package com.nicholasdoglio.eyebleach.ui.photolist;

import android.arch.lifecycle.LiveData;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.data.source.RedditPostRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Nicholas Doglio
 */
public class PhotoListPresenter implements PhotoListContract.Presenter {
    private static final int FIRST_LOAD = 100;
    private static final int LOAD_MORE = 60;
    private final LiveData<List<ChildData>> photoList;
    private final RedditPostRepository repository;
    private PhotoListContract.View photoGridView;
    private CompositeDisposable photoGridDisposable;

    @Inject
    PhotoListPresenter(RedditPostRepository redditPostRepository) {
        repository = redditPostRepository;
        photoGridDisposable = new CompositeDisposable();
        photoList = repository.getPostsLive();
    }

    @Override
    public void load() {
        photoGridDisposable.add(repository.getPostsFirstLoad(FIRST_LOAD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ChildData>>() {
                    @Override
                    public void onSuccess(List<ChildData> childData) {
                        photoGridView.load(childData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

    void loadMore() {
        photoGridDisposable.add(repository.getMorePosts(LOAD_MORE)
                .subscribeOn(Schedulers.io())
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
        photoGridDisposable.clear();
    }

    @Override
    public void takeView(PhotoListContract.View view) {
        this.photoGridView = view;
    }

    @Override
    public void dropView() {
        photoGridView = null;
    }

    LiveData<List<ChildData>> getPhotoList() {
        return photoList;
    }
}