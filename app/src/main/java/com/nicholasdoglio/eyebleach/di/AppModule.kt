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
package com.nicholasdoglio.eyebleach.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.nicholasdoglio.eyebleach.data.source.local.RedditPostDatabase
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * @author Nicholas Doglio
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun context(application: Application): Context =
        application


    @Provides
    @Singleton
    fun room(application: Application): RedditPostDatabase =
        Room.databaseBuilder(application, RedditPostDatabase::class.java, "reddit_posts_db")
            .build()


    @Provides
    @Singleton
    fun redditService(): RedditService {
        return Retrofit.Builder().baseUrl("https://www.reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RedditService::class.java)
    }

}