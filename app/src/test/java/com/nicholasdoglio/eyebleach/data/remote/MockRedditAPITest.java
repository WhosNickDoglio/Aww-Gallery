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
import com.nicholasdoglio.eyebleach.di.DaggerTestAppComponent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Mockito.when;

/**
 * @author Nicholas Doglio
 */
public class MockRedditAPITest {

    @Inject
    MockRedditAPI mockRedditAPI;

    private int limit = 100;
    private String after = "";
    private MockWebServer mockWebServer;
    private TestSubscriber<Multireddit> testSubscriber;

    @Before
    public void setUp() throws Exception {
        testSubscriber = new TestSubscriber<>();
        mockWebServer = new MockWebServer();

        DaggerTestAppComponent
                .builder()
                .build();

        mockWebServer.start();
    }

    @Test
    public void SuccessfulNetworkCall() throws Exception {
        when(mockRedditAPI.getGalleryFromMulti(limit, after))
                .thenReturn(Single.just(multireddit()));
    }

    @Test
    public void FailedNetworkCall() throws Exception {
        mockRedditAPI.getGalleryFromMulti(limit, after);
    }

    @After
    public void tearDown() throws Exception {
        testSubscriber.dispose();
        mockWebServer.shutdown();
    }

    private Multireddit multireddit() {
        Multireddit multireddit = new Multireddit();

        //fill this with dummy data

        return multireddit;
    }
}