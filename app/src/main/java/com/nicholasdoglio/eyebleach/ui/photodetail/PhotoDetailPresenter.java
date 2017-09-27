package com.nicholasdoglio.eyebleach.ui.photodetail;

import com.nicholasdoglio.eyebleach.data.source.RedditPostRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {
    private static final int IMAGES_LOAD_VIEWPAGER = 10;
    public PhotoDetailContract.View view;
    private RedditPostRepository repository;
    private CompositeDisposable compositeDisposable;

    @Inject
    PhotoDetailPresenter(RedditPostRepository redditPostRepository) {
        repository = redditPostRepository;
        compositeDisposable = new CompositeDisposable();
    }

    public void load() {
        compositeDisposable.add(repository.getPosts(IMAGES_LOAD_VIEWPAGER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(childData -> view.updateList(childData)));
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    public void takeView(PhotoDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}
