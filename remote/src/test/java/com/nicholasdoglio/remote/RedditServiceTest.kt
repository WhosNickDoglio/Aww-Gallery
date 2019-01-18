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