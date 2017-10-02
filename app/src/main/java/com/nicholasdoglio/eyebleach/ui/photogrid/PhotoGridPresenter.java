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
package com.nicholasdoglio.eyebleach.ui.photogrid;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.data.source.RedditPostRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Nicholas Doglio
 */
public class PhotoGridPresenter implements PhotoGridContract.Presenter {
    private static final int IMAGES_LOADED_RECYCLERVIEW = 48;
    final LiveData<PagedList<ChildData>> childData;
    private final RedditPostRepository repository;
    public PhotoGridContract.View view;
    private CompositeDisposable disposable;

    @Inject
    PhotoGridPresenter(RedditPostRepository redditPostRepository) {
        repository = redditPostRepository;
        disposable = new CompositeDisposable();
        childData = repository.pagedList().create(0,
                new PagedList.Config.Builder()
                        .setPageSize(18)
                        .setPrefetchDistance(6)
                        .build());
    }

    @Override
    public void load() {
        disposable.add(repository.getFirstLoadPosts(IMAGES_LOADED_RECYCLERVIEW)
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    void loadMore() {
        disposable.add(repository.getMorePosts(IMAGES_LOADED_RECYCLERVIEW)
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    @Override
    public void clear() {
        disposable.clear();
    }

    @Override
    public void takeView(PhotoGridContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}