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

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import retrofit2.mock.BehaviorDelegate;

/**
 * @author Nicholas Doglio
 */
public class MockRedditService implements RedditService {

    private final BehaviorDelegate<RedditService> behaviorDelegate;
    private MockHelper mockHelper;

    public MockRedditService(BehaviorDelegate<RedditService> behaviorDelegate, MockHelper mockHelper) {
        this.behaviorDelegate = behaviorDelegate;
        this.mockHelper = mockHelper;
    }

    @NotNull
    @Override
    public Single<Multireddit> getGalleryFromMulti(int limit, @NotNull String after) {
        return behaviorDelegate.returningResponse(mockHelper.getMulti()).getGalleryFromMulti(limit, after);
    }
}