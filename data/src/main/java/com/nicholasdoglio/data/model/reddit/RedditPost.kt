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
package com.nicholasdoglio.data.model.reddit

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * @author Nicholas Doglio
 */
@Entity
data class RedditPost(
    @PrimaryKey
    @Json(name = "name")
    val name: String,
    @Json(name = "selftext")
    val selftext: String?,
    @Json(name = "thumbnail")
    val thumbnail: String?,
    @Json(name = "permalink")
    val permalink: String,
    @Json(name = "url")
    val url: String?,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "subreddit")
    val subreddit: String?,
    @Json(name = "over_18")
    val over18: Boolean?
) {


    fun fullUrl() = "https://reddit.com$permalink"
}
