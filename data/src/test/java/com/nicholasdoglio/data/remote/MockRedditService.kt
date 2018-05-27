package com.nicholasdoglio.data.remote

import com.nicholasdoglio.data.model.reddit.Listing
import com.nicholasdoglio.data.source.remote.RedditService
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate

class MockRedditService(private val behaviorDelegate: BehaviorDelegate<RedditService>) :
    RedditService {

    override fun requestMultireddit(limit: Int): Call<Listing> {
        return behaviorDelegate.returningResponse("").requestMultireddit(limit)
    }

    override fun requestMultiredditAfter(limit: Int, after: String): Call<Listing> {
        return behaviorDelegate.returningResponse("").requestMultiredditAfter(limit, after)
    }

    override fun requestBySubreddit(subreddit: String): Call<Listing> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}