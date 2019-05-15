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
package com.nicholasdoglio.eyebleach.di;

import com.nicholasdoglio.eyebleach.data.source.remote.RedditAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * @author Nicholas Doglio
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    RedditAPI providesRedditService() {
        return new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RedditAPI.class);
    }

}