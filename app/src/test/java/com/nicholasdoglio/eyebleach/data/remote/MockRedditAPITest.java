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
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * @author Nicholas Doglio
 */
public class MockRedditAPITest {

    private int limit = 100;
    private RedditAPI redditAPI;
    private String after = "";
    private MockWebServer mockWebServer;
    private TestSubscriber<Multireddit> testSubscriber;
    private Moshi moshi;
    private TestObserver testObserver;

    @Before
    public void setUp() throws Exception {
        testSubscriber = new TestSubscriber<>();
        mockWebServer = new MockWebServer();

        redditAPI = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RedditAPI.class);

        moshi = new Moshi.Builder()
                .build();

        mockWebServer.start();
    }

    @Test
    public void SuccessfulNetworkCall() throws Exception {
        testObserver = new TestObserver();
        mockWebServer.enqueue(new MockResponse().setBody(""));// load from json file

        JsonAdapter<Multireddit> jsonAdapter = moshi.adapter(Multireddit.class);
        Multireddit multireddit = jsonAdapter.fromJson("");


        redditAPI.getGalleryFromMulti(limit, after)
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertValue("");
    }

    @Test
    public void FailedNetworkCall() throws Exception { //Error Code 401
        testObserver = new TestObserver();
        mockWebServer.enqueue(new MockResponse().setBody(""));

        redditAPI.getGalleryFromMulti(limit, after);


        testObserver.assertNoErrors();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertValue("");
    }

    @After
    public void tearDown() throws Exception {
        testSubscriber.dispose();
        testObserver.dispose();
        mockWebServer.shutdown();
    }
}