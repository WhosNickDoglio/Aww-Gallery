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
package com.nicholasdoglio.data.source.local

import android.arch.paging.PagedList
import com.nicholasdoglio.data.model.reddit.Listing
import com.nicholasdoglio.data.model.reddit.RedditPost
import com.nicholasdoglio.data.source.remote.RedditService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class RedditPostBoundaryCallback(
    private val redditService: RedditService,
    private val backgroundExecutor: Executor,
    private val handleResponse: (Listing?) -> Unit
) :
    PagedList.BoundaryCallback<RedditPost>() {

    private val LIMIT = 100

    override fun onZeroItemsLoaded() {
        redditService.requestMultireddit(LIMIT).enqueue(redditServiceCallback())
    }

    override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
        redditService.requestMultiredditAfter(LIMIT, itemAtEnd.name)
            .enqueue(redditServiceCallback())

    }

    private fun insertItemsIntoDb(
        response: Response<Listing>
    ) {
        backgroundExecutor.execute {
            handleResponse(response.body())
        }
    }


    private fun redditServiceCallback(): Callback<Listing> {
        return object : Callback<Listing> {
            override fun onFailure(
                call: Call<Listing>,
                t: Throwable
            ) {
            }

            override fun onResponse(
                call: Call<Listing>,
                response: Response<Listing>
            ) {
                insertItemsIntoDb(response)
            }
        }
    }

}