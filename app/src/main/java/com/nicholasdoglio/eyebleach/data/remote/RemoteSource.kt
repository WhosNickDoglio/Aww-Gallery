package com.nicholasdoglio.eyebleach.data.remote

import com.nicholasdoglio.eyebleach.data.local.RedditPost
import com.nicholasdoglio.eyebleach.util.DispatcherProvider
import com.nicholasdoglio.eyebleach.util.toRedditPosts
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val redditService: RedditService,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun requestsPosts(after: String = ""): List<RedditPost> {
        val response = withContext(dispatcherProvider.network) {
            redditService.multiPosts(after).await()
        }

        val data = withContext(dispatcherProvider.background) {
            response.toRedditPosts()
        }

        return data
    }
}
