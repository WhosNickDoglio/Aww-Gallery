package com.nicholasdoglio.eyebleach.ui.photogrid;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.data.source.RedditPostRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PhotoGridPresenter implements PhotoGridContract.Presenter {
    private static final int IMAGES_LOADED_RECYCLERVIEW = 50;
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
                        .setPrefetchDistance(18)
                        .build());
    }

    @Override
    public void load() {
        disposable.add(repository.getPosts(IMAGES_LOADED_RECYCLERVIEW)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());


    }

    public void loadMore() {
        disposable.add(repository.getMorePosts(IMAGES_LOADED_RECYCLERVIEW)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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