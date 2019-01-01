package com.nicholasdoglio.eyebleach.di

import dagger.Module

/**
 * @author Nicholas Doglio
 */
@Module(includes = [(ViewModelModule::class)])
object AppModule {

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