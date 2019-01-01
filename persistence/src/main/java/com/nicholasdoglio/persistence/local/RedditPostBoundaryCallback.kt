package com.nicholasdoglio.persistence.local

//class RedditPostBoundaryCallback(
//  private val redditService: RedditService,
//  private val backgroundExecutor: Executor,
//  private val handleResponse: (Listing?) -> Unit
//) :
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
//}