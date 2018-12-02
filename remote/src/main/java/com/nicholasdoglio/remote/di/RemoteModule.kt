package com.nicholasdoglio.remote.di

import com.nicholasdoglio.remote.RedditService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteModule {

    private const val BASE_URL = "https://www.reddit.com/"

    //    @Provides
    @JvmStatic fun interceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    //    @Provides
    @JvmStatic fun okHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    //    @Provides @Singleton
    @JvmStatic fun schoolService(okHttpClient: OkHttpClient): RedditService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RedditService::class.java)
}