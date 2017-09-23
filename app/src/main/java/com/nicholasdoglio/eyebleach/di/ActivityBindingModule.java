package com.nicholasdoglio.eyebleach.di;

import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailActivity;
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailModule;
import com.nicholasdoglio.eyebleach.ui.photogrid.PhotoGridActivity;
import com.nicholasdoglio.eyebleach.ui.photogrid.PhotoGridModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = PhotoGridModule.class)
    abstract PhotoGridActivity bindPhotoGridActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PhotoDetailModule.class)
    abstract PhotoDetailActivity bindPhotoDetailActivity();
}