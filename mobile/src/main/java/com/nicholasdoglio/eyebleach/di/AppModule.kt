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

import dagger.Module

/**
 * @author Nicholas Doglio
 */
@Module(includes = [(ViewModelModule::class)])
object AppModule {
    private val redditBaseUrl = "https://www.reddit.com/"

//    @Provides
//    @Singleton
//    fun room(application: Application): AwwGalleryDatabase =
//        Room.databaseBuilder(application, AwwGalleryDatabase::class.java, "reddit_posts_db")
//            .build()
//
//    @Provides
//    @Singleton
//    fun redditService(): RedditService = Retrofit.Builder().baseUrl(Constants.redditBaseUrl)
//        .addConverterFactory(MoshiConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(RedditService::class.java)
}