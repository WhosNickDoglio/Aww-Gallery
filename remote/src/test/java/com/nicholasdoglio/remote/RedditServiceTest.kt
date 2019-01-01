package com.nicholasdoglio.remote

import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * @author Nicholas Doglio
 * Tests For the Reddit Service class using MockWebServer
 */
class RedditServiceTest {

  private val limit = 100
  private lateinit var retrofit: Retrofit
  private lateinit var mockRetrofit: MockRetrofit
  private lateinit var networkBehavior: NetworkBehavior
  private lateinit var behaviorDelegate: BehaviorDelegate<RedditService>
  private lateinit var mockRedditService: MockRedditService

  @Before
  fun setUp() {
    retrofit = Retrofit.Builder()
      .baseUrl("https://reddit.com")
      .addConverterFactory(MoshiConverterFactory.create())
      .build()

    networkBehavior = NetworkBehavior.create()

    mockRetrofit = MockRetrofit.Builder(retrofit)
      .networkBehavior(networkBehavior)
      .build()

    behaviorDelegate = mockRetrofit.create(RedditService::class.java)

    mockRedditService = MockRedditService(behaviorDelegate)
  }

  @Test
  fun initialCallSuccessful() {

    val testResponse = mockRedditService.requestMultireddit(limit).execute()
    val testListing = testResponse.body()

    val data = testListing!!.data
//    val dataList = data.redditChildrenResponses

    assertTrue { testResponse.isSuccessful }
    assertNotNull(testListing)
    assertNotNull(data)

  }

  @Test
  fun afterCallSuccessful() {
    val testResponse = mockRedditService.requestMultiredditAfter(limit, "").execute()

  }
}