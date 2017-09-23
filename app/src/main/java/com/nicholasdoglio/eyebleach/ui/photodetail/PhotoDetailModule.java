package com.nicholasdoglio.eyebleach.ui.photodetail;

import com.nicholasdoglio.eyebleach.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PhotoDetailModule {

    @ActivityScoped
    @Binds
    abstract PhotoDetailContract.Presenter photoDetailPresenter(PhotoDetailPresenter presenter);
}
