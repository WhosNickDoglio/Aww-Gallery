/*
    Aww Gallery
    Copyright (C) 2017  Nicholas Doglio

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nicholasdoglio.eyebleach.data.remote.MockRetrofit

import com.nicholasdoglio.eyebleach.data.model.reddit.RedditListing
import com.nicholasdoglio.eyebleach.data.remote.MockHelper
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

/**
 * @author Nicholas Doglio
 */
class MockRedditServiceTest {
    private var behavior: NetworkBehavior? = null
    private var retrofit: Retrofit? = null
    private var mockHelper: MockHelper? = null
    private var mockRetrofit: MockRetrofit? = null
    private var testObserver: TestObserver<RedditListing>? = null
    private var mockRedditService: RedditService? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockHelper = MockHelper()
        testObserver = TestObserver()
        behavior = NetworkBehavior.create()

        retrofit = Retrofit.Builder().baseUrl("https://reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        mockRetrofit = MockRetrofit.Builder(retrofit!!).networkBehavior(behavior!!).build()

        val delegate = mockRetrofit!!.create(RedditService::class.java)

        mockRedditService = MockRedditService(delegate, mockHelper!!)
    }

    @Test
    @Throws(Exception::class)
    fun successfulNetworkCall() {
        behavior!!.setErrorPercent(0)

        mockRedditService!!.getGalleryFromMulti(LIMIT, AFTER).subscribe(testObserver!!)

        testObserver!!.assertComplete()
        testObserver!!.assertNoErrors()
    }

    @Test
    @Throws(Exception::class)
    fun failedNetworkCall() {
        behavior!!.setFailurePercent(100)

    }

    companion object {
        private val LIMIT = 100
        private val AFTER = ""
    }
}