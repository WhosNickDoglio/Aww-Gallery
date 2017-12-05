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
package com.nicholasdoglio.eyebleach.data.remote.MockWebServer;

import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.remote.MockHelper;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * @author Nicholas Doglio
 *         Tests For the Reddit Service class using MockWebServer
 */
public class RedditSeviceTestMockWebServer {

    private int limit = 100;
    private String after = "";
    private MockWebServer mockWebServer;
    private RedditService redditService;
    private Multireddit multireddit;
    private MockHelper mockHelper;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();

        mockHelper = new MockHelper();

        redditService = new Retrofit.Builder().baseUrl(mockWebServer.url("").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RedditService.class);

        multireddit = mockHelper.getMulti();
    }

    @Test
    public void networkCallSuccessful() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody(MockHelper.MOCK_JSON));

        Multireddit testMultireddit = redditService.getGalleryFromMulti(limit, after).blockingGet();

        assertEquals(multireddit.getKind(), testMultireddit.getKind());
        assertEquals(multireddit.getData().getAfter(), testMultireddit.getData().getAfter());
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }
}
