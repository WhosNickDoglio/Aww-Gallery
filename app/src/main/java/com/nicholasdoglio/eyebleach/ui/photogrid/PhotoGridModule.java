package com.nicholasdoglio.eyebleach.ui.photogrid;

import com.nicholasdoglio.eyebleach.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PhotoGridModule {

    @ActivityScoped
    @Binds
    abstract PhotoGridContract.Presenter photoGridPresenter(PhotoGridPresenter presenter);
}