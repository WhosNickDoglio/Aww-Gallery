package com.nicholasdoglio.eyebleach;

import com.nicholasdoglio.eyebleach.di.AppComponent;
import com.nicholasdoglio.eyebleach.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AwwGalleryApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
