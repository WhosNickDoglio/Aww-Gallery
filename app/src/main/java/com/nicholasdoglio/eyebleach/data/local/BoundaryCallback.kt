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
import com.nicholasdoglio.eyebleach.data.remote.RedditService
import com.nicholasdoglio.eyebleach.util.SchedulersProvider
import com.nicholasdoglio.eyebleach.util.toRedditPosts
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class BoundaryCallback
@Inject constructor(
    private val redditService: RedditService,
    private val localSource: LocalSource,
    private val schedulersProvider: SchedulersProvider
) : PagedList.BoundaryCallback<RedditPost>() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        disposable += requestPostsAndSaveThem("").subscribe()
    }

    override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
        super.onItemAtEndLoaded(itemAtEnd)
        disposable += requestPostsAndSaveThem(itemAtEnd.name).subscribe()
    }

    private fun requestPostsAndSaveThem(after: String): Completable =
        redditService.multiPosts(after)
            .subscribeOn(schedulersProvider.network)

            .observeOn(schedulersProvider.background)
            .map { response -> response.toRedditPosts() }

            .observeOn(schedulersProvider.database)
            .flatMapCompletable { posts -> localSource.insertPosts(posts) }

    fun clearNetworkCalls() {
        disposable.clear()
    }
}
