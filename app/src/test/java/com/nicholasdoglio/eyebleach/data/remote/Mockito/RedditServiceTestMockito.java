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
package com.nicholasdoglio.eyebleach.data.remote.Mockito;

import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.remote.MockHelper;
import com.nicholasdoglio.eyebleach.data.source.remote.RedditService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Nicholas Doglio
 */
@RunWith(MockitoJUnitRunner.class)
public class RedditServiceTestMockito {

    @Mock
    private RedditService redditService;

    private int limit = 100;
    private String after = "";
    private MockHelper mockHelper;
    private TestObserver<Multireddit> testObserver;
    private Multireddit testMultireddit;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockHelper = new MockHelper();
        testObserver = new TestObserver<>();
        testMultireddit = mockHelper.getMulti();
        when(redditService.getGalleryFromMulti(limit, after)).thenReturn(Single.just(mockHelper.getMulti()));
    }

    @Test
    public void networkCallCompleted() throws Exception {
        redditService.getGalleryFromMulti(limit, after).subscribe(testObserver);
        testObserver.assertComplete().assertNoErrors();
    }

    @Test
    public void networkCallSuccessful() throws Exception {
        redditService.getGalleryFromMulti(limit, after).subscribe(testObserver);
        Multireddit multireddit = redditService.getGalleryFromMulti(limit, after).blockingGet();

        assertEquals(multireddit.getKind(), testMultireddit.getKind());
        assertEquals(multireddit.getData().getModhash(), testMultireddit.getData().getModhash());
    }
}
