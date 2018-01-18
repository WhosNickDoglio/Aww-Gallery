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
package com.nicholasdoglio.eyebleach.data.model.reddit

import com.squareup.moshi.Json

/**
 * @author Nicholas Doglio
 *
 * thing: Reddit JSON base class which is the top level pull from the Reddit API
 *
 * @param kind: identifies the type of object is being retrieved, this is a thing
 * @param data: the object that is identified, this is a listing
 */
class RedditListing {
    @Json(name = "kind")
    var kind: String? = null
    @Json(name = "data")
    var data: RedditListingData? = null
}
