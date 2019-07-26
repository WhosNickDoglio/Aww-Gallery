package com.nicholasdoglio.eyebleach.data.remote

import com.nicholasdoglio.eyebleach.data.util.toRedditPosts
import com.nicholasdoglio.eyebleach.db.RedditPost
import com.nicholasdoglio.eyebleach.util.DispatcherProvider
import com.nicholasdoglio.eyebleach.util.Resource
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber

class RemoteSource @Inject constructor(
    private val redditService: RedditService,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun requestsPosts(after: String = ""): List<RedditPost> {
        val response = redditService.multiPosts(after).body()

        return withContext(dispatcherProvider.background) {
            response?.toRedditPosts() ?: emptyList()
        }
    }

    suspend fun posts(after: String = ""): Flow<Resource<List<RedditPost>>> = flow {
        emit(Resource.loading(null))
        try {
            val response = redditService.multiPosts(after).body()

            val data = withContext(dispatcherProvider.background) {
                response?.toRedditPosts() ?: emptyList()
            }

            if (data.isEmpty()) {
                emit(Resource.error("No data", null))
            } else {
                emit(Resource.success(data))
            }
        } catch (e: IOException) {
            Timber.e(e)
            emit(Resource.error(e.message ?: "There is no internet connection", null))
        } catch (e: HttpException) {
            Timber.e(e)
            emit(Resource.error(e.message ?: "This is an error!", null))
        }
    }
}
