/*
 * MIT License
 *
 * Copyright (c) 2019 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nicholasdoglio.redditapi

import com.nicholasdoglio.redditapi.response.ListingResponse
import com.nicholasdoglio.redditapi.response.SubredditAboutResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Should this ever not be 100?
private const val LIMIT = 100

// TODO change to suspend functions when Coroutines support is official
interface RedditService {

    @GET("user/NicholasDoglio/m/awwgallery/.json")
    fun multireddit(
        @Query("limit") limit: Int = LIMIT,
        @Query("after") after: String
    ): Deferred<Response<ListingResponse>>

    // @GET("user/NicholasDoglio/m/awwgallery/.json")
    // suspend fun multireddit(
    //     @Query("limit") limit: Int = LIMIT,
    //     @Query("after") after: String
    // ): <Response<ListingResponse>

    @GET("r/{subreddit}/.json")
    fun subreddit(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int = LIMIT,
        @Query("after") after: String
    ): Deferred<Response<ListingResponse>>

    // @GET("r/{subreddit}/.json")
    // suspend fun subreddit(
    //     @Path("subreddit") subreddit: String,
    //     @Query("limit") limit: Int = LIMIT,
    //     @Query("after") after: String
    // ): Response<ListingResponse>

    @GET("r/{subreddit}/about.json")
    fun subredditInfo(@Path("subreddit") subreddit: String): Deferred<Response<SubredditAboutResponse>>

    // @GET("r/{subreddit}/about.json")
    // suspend fun subredditInfo(@Path("subreddit") subreddit: String): Response<SubredditAboutResponse>
}
