package com.nicholasdoglio.eyebleach.di;

import android.app.Application;

import com.nicholasdoglio.eyebleach.AwwGalleryApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class,})

public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(AwwGalleryApp application);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}