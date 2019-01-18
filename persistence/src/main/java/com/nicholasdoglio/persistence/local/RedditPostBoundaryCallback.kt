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

package com.nicholasdoglio.persistence.local

// class RedditPostBoundaryCallback(
//  private val redditService: RedditService,
//  private val backgroundExecutor: Executor,
//  private val handleResponse: (Listing?) -> Unit
// ) :
//  PagedList.BoundaryCallback<RedditPost>() {
//
//  private val LIMIT = 100
//
//  override fun onZeroItemsLoaded() {
//    redditService.requestMultireddit(LIMIT).enqueue(redditServiceCallback())
//  }
//
//  override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
//    redditService.requestMultiredditAfter(LIMIT, itemAtEnd.name)
//      .enqueue(redditServiceCallback())
//
//  }
//
//  private fun insertItemsIntoDb(
//    response: Response<Listing>
//  ) {
//    backgroundExecutor.execute {
//      handleResponse(response.body())
//    }
//  }
//
//  private fun redditServiceCallback(): Callback<Listing> {
//    return object : Callback<Listing> {
//      override fun onFailure(
//        call: Call<Listing>,
//        t: Throwable
//      ) {
//      }
//
//      override fun onResponse(
//        call: Call<Listing>,
//        response: Response<Listing>
//      ) {
//        insertItemsIntoDb(response)
//      }
//    }
//  }
// }