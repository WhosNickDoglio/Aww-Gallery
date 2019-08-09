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

package com.nicholasdoglio.eyebleach.data.local

import com.nicholasdoglio.eyebleach.db.RedditPost
import com.nicholasdoglio.eyebleach.db.RedditPostQueries
import com.nicholasdoglio.eyebleach.util.DispatcherProvider
import com.squareup.sqldelight.android.paging.QueryDataSourceFactory
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import javax.inject.Inject
import kotlinx.coroutines.withContext

class LocalSource @Inject constructor(
    private val redditPostQueries: RedditPostQueries,
    private val dispatcherProvider: DispatcherProvider
) {

    val dataSource = QueryDataSourceFactory(
        queryProvider = redditPostQueries::posts,
        countQuery = redditPostQueries.postCount()
    )

    val count = redditPostQueries.postCount().asFlow().mapToOne(dispatcherProvider.database)

    suspend fun findPostById(id: String): RedditPost = withContext(dispatcherProvider.database) {
        redditPostQueries.findPostById(id).executeAsOne()
    }

    suspend fun deleteAllPosts() = withContext(dispatcherProvider.database) {
        redditPostQueries.deleteAll()
    }

    suspend fun insertPosts(data: List<RedditPost>) = withContext(dispatcherProvider.database) {
        data.forEach {
            redditPostQueries.insert(
                url = it.url,
                name = it.name,
                thumbnail = it.thumbnail,
                permalink = it.permalink
            )
        }
    }
}
