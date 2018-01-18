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
package com.nicholasdoglio.eyebleach.data.remote.Mockito

import com.nicholasdoglio.eyebleach.data.model.reddit.RedditListing
import com.nicholasdoglio.eyebleach.data.remote.MockHelper
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Nicholas Doglio
 */
@RunWith(MockitoJUnitRunner::class)
class RedditServiceTestMockito {

    @Mock
    private val redditService: RedditService? = null

    private val limit = 100
    private val after = ""
    private var mockHelper: MockHelper? = null
    private var testObserver: TestObserver<RedditListing>? = null
    private var testRedditListing: RedditListing? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockHelper = MockHelper()
        testObserver = TestObserver()
        testRedditListing = mockHelper!!.multi
        `when`(
            redditService!!.getGalleryFromMulti(
                limit,
                after
            )
        ).thenReturn(Single.just(mockHelper!!.multi))
    }

    @Test
    @Throws(Exception::class)
    fun networkCallCompleted() {
        redditService!!.getGalleryFromMulti(limit, after).subscribe(testObserver!!)
        testObserver!!.assertComplete().assertNoErrors()
    }

    @Test
    @Throws(Exception::class)
    fun networkCallSuccessful() {
        redditService!!.getGalleryFromMulti(limit, after).subscribe(testObserver!!)
        val redditListing = redditService.getGalleryFromMulti(limit, after).blockingGet()

        assertEquals(redditListing.kind, testRedditListing!!.kind)
        assertEquals(redditListing.data!!.modhash, testRedditListing!!.data!!.modhash)
    }
}
