package com.nicholasdoglio.remote

import com.nicholasdoglio.remote.entities.Children
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Nicholas Doglio
 * Retrofit interface for Reddit.com
 */
interface RedditService {

  /**
   * https://reddit.com/user/NicholasDoglio/m/awwgallery
   *
   * @param limit: the number of posts are part of the initial pull
   *
   */
  @GET("user/NicholasDoglio/m/awwgallery/.json")
  fun requestMultireddit(@Query("limit") limit: Int): Call<Children>

  @GET("user/NicholasDoglio/m/awwgallery/.json")
  fun requestMultiredditAfter(@Query("limit") limit: Int, @Query("after") after: String): Call<Children>

  @GET("r/{subreddit}/.json")
  fun requestBySubreddit(@Path("subreddit") subreddit: String): Call<Children>
}