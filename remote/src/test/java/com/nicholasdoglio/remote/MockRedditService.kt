package com.nicholasdoglio.remote

import com.nicholasdoglio.remote.entities.Children
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate

class MockRedditService(private val behaviorDelegate: BehaviorDelegate<RedditService>) :
  RedditService {

  override fun requestMultireddit(limit: Int): Call<Children> {
    return behaviorDelegate.returningResponse("").requestMultireddit(limit)
  }

  override fun requestMultiredditAfter(limit: Int, after: String): Call<Children> {
    return behaviorDelegate.returningResponse("").requestMultiredditAfter(limit, after)
  }

  override fun requestBySubreddit(subreddit: String): Call<Children> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}