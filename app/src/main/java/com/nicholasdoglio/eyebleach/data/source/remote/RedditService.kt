package com.nicholasdoglio.eyebleach.data.source.remote

import com.nicholasdoglio.eyebleach.data.model.Multireddit
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {
    @GET("user/NicholasDoglio/m/awwgallery/.json")
    fun getMultiPosts(@Query("limit") limit: Int, @Query("after") after: String): Flowable<Multireddit>

    @GET("r/{subreddit}/.json")
    fun getSubPosts(@Path("subreddit") subreddit: String, @Query("limit") limit: Int, @Query("after") after: String): Flowable<Multireddit>
}
