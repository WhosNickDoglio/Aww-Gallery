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
package com.nicholasdoglio.eyebleach.data.remote.MockWebServer

import com.nicholasdoglio.eyebleach.data.model.reddit.RedditListing
import com.nicholasdoglio.eyebleach.data.remote.MockHelper
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author Nicholas Doglio
 * Tests For the Reddit Service class using MockWebServer
 */
class RedditSeviceTestMockWebServer {

    private val limit = 100
    private val after = ""
    private var mockWebServer: MockWebServer? = null
    private var redditService: RedditService? = null
    private var redditListing: RedditListing? = null
    private var mockHelper: MockHelper? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockWebServer = MockWebServer()

        mockHelper = MockHelper()

        redditService = Retrofit.Builder().baseUrl(mockWebServer!!.url("").toString())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RedditService::class.java)

        redditListing = mockHelper!!.multi
    }

    @Test
    @Throws(Exception::class)
    fun networkCallSuccessful() {
        mockWebServer!!.enqueue(MockResponse().setBody(MockHelper.MOCK_JSON))

        val testRedditListing = redditService!!.getGalleryFromMulti(limit, after).blockingGet()

        assertEquals(redditListing!!.kind, testRedditListing.kind)
        assertEquals(redditListing!!.data!!.after, testRedditListing.data!!.after)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer!!.shutdown()
    }
}
