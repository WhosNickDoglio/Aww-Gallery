package com.nicholasdoglio.eyebleach.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.nicholasdoglio.eyebleach.data.source.local.RedditPostDatabase;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    RedditService providesRedditService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RedditService.class);
    }

    @Provides
    @Singleton
    RedditPostDatabase provideRoom(Application application) {
        return Room.databaseBuilder(application, RedditPostDatabase.class, "reddit_posts_db")
                .build();
    }
}