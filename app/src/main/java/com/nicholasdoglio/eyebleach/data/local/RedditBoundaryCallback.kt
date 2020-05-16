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

import androidx.paging.PagedList
import com.nicholasdoglio.eyebleach.data.remote.RemoteSource
import com.nicholasdoglio.eyebleach.util.DispatcherProvider
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RedditBoundaryCallback
@Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    private val dispatcherProvider: DispatcherProvider
) : PagedList.BoundaryCallback<RedditPost>(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = job + dispatcherProvider.main

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        launch { requestPostsAndSaveThem() }
    }

    override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
        super.onItemAtEndLoaded(itemAtEnd)
        launch { requestPostsAndSaveThem(itemAtEnd.name) }
    }

    private suspend fun requestPostsAndSaveThem(after: String = "") {
        val data = remoteSource.requestsPosts(after)

        localSource.insertPosts(data)
    }

    fun clear() {
        coroutineContext.cancel()
    }
}
