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
package com.nicholasdoglio.eyebleach.data

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.nicholasdoglio.eyebleach.data.local.LocalSource
import com.nicholasdoglio.eyebleach.data.local.RedditBoundaryCallback
import com.nicholasdoglio.eyebleach.db.RedditPost
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow

@Singleton
class RedditPostRepository @Inject constructor(
    private val localSource: LocalSource,
    private val callback: RedditBoundaryCallback
) {
    private val refreshChannel = ConflatedBroadcastChannel<Boolean>()
    val refresh = refreshChannel.asFlow()

    val posts: LiveData<PagedList<RedditPost>> = localSource.dataSource.toLiveData(
        config = Config(PAGE_SIZE),
        boundaryCallback = callback
    )

    suspend fun findPostById(id: String): RedditPost = localSource.findPostById(id)

    suspend fun refresh() {

        refreshChannel.offer(true)

        localSource.deleteAllPosts()

        refreshChannel.offer(false)
    }

    fun clear() {
        callback.clear()
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}
