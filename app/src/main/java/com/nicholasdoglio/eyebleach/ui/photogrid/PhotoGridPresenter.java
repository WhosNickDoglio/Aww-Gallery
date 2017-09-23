package com.nicholasdoglio.eyebleach.ui.photogrid;


import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.data.model.Multireddit;
import com.nicholasdoglio.eyebleach.data.source.RedditPostRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PhotoGridPresenter implements PhotoGridContract.Presenter {
    private static final int IMAGES_LOADED_RECYCLERVIEW = 50;
    private final RedditPostRepository repository;
    public PhotoGridContract.View view;
    private CompositeDisposable disposable;

    @Inject
    PhotoGridPresenter(RedditPostRepository redditPostRepository) {
        repository = redditPostRepository;
        disposable = new CompositeDisposable();
    }

    public void swipeLoad() {

    }

    @Override
    public void load() {//Can I combine initial load and loadMore?
        disposable.add(repository.getInitialLoad(IMAGES_LOADED_RECYCLERVIEW)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(multireddit -> view.updateListMulti(multireddit)));


//        disposable.add(repository.getPosts(IMAGES_LOADED_RECYCLERVIEW)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(dataList -> view.updateListChildData(dataList)));
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

    void filterForImages(Multireddit multireddit, List<ChildData> childData) {
        for (int i = 0; i < multireddit.getData().getChildren().size(); i++) {
//            if (multireddit.getData().getChildren().get(i).getChildData().getUrl().contains(".jpg") ||
//                    multireddit.getData().getChildren().get(i).getChildData().getUrl().contains(".png")) {
            childData.add(multireddit.getData().getChildren().get(i).getChildData());
        }
    }
//    }
}



