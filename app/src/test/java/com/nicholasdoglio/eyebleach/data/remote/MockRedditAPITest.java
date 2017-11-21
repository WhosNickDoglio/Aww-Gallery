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
package com.nicholasdoglio.eyebleach.data.remote;

import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditAPI;
import io.reactivex.Single;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Nicholas Doglio
 */
public class MockRedditAPITest {

  private static final int LIMIT = 100;
  private static final String AFTER = "";
  private RedditAPI redditAPI;
  private MockWebServer mockWebServer;
  private MockHelper mockHelper;

  @Mock private RedditAPI mockRedditAPI;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockHelper = new MockHelper();
    mockWebServer = new MockWebServer();

    redditAPI = new Retrofit.Builder().baseUrl(mockWebServer.url("/"))
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RedditAPI.class);
  }

  @Test public void SuccessfulNetworkCall() throws Exception {
    mockWebServer.enqueue(new MockResponse().setBody(MockHelper.MockJSON));// load from json file fix this later
    when(mockRedditAPI.getGalleryFromMulti(LIMIT, AFTER)).thenReturn(Single.just(mockHelper.getMulti()));

    Multireddit testMulti = redditAPI.getGalleryFromMulti(LIMIT, AFTER).blockingGet();
    Multireddit multireddit = mockHelper.getMulti();

    assertEquals(testMulti.getKind(), multireddit.getKind());
  }

  @After public void tearDown() throws Exception {
    mockWebServer.shutdown();
  }
}