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
    fun requestMultiredditAfter(
        @Query("limit") limit: Int,
        @Query("after") after: String
    ): Call<Children>

    @GET("r/{subreddit}/.json")
    fun requestBySubreddit(@Path("subreddit") subreddit: String): Call<Children>
}