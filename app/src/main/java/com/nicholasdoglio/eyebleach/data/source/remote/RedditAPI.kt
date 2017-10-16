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
package com.nicholasdoglio.eyebleach.data.source.remote

import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Nicholas Doglio
 */
interface RedditAPI {
    @GET("user/NicholasDoglio/m/awwgallery/.json")
    fun getMultiPosts(@Query("limit") limit: Int,
                      @Query("after") after: String): Single<Multireddit>

    @GET("r/{subreddit}/.json")
    fun getSubPosts(@Path("subreddit") subreddit: String,
                    @Query("limit") limit: Int,
                    @Query("after") after: String): Flowable<Multireddit>
}
