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
package com.nicholasdoglio.eyebleach.data.remote.MockRetrofit;

import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.remote.MockHelper;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * @author Nicholas Doglio
 */
public class MockRedditServiceTest {
    private static final int LIMIT = 100;
    private static final String AFTER = "";
    private NetworkBehavior behavior;
    private Retrofit retrofit;
    private MockHelper mockHelper;
    private MockRetrofit mockRetrofit;
    private TestObserver<Multireddit> testObserver;
    private RedditService mockRedditService;

    @Before
    public void setUp() throws Exception {
        mockHelper = new MockHelper();
        testObserver = new TestObserver<>();
        behavior = NetworkBehavior.create();

        retrofit = new Retrofit.Builder().baseUrl("https://reddit.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(behavior).build();

        BehaviorDelegate<RedditService> delegate = mockRetrofit.create(RedditService.class);

        mockRedditService = new MockRedditService(delegate, mockHelper);
    }

    @Test
    public void successfulNetworkCall() throws Exception {
        behavior.setErrorPercent(0);

        mockRedditService.getGalleryFromMulti(LIMIT, AFTER).subscribe(testObserver);

        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void failedNetworkCall() throws Exception {
        behavior.setFailurePercent(100);

    }
}