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

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * @author Nicholas Doglio
 */
@Entity
class ChildData {
    @Json(name = "selftext")
    var selftext: String? = null
    @Json(name = "thumbnail")
    var thumbnail: String? = null
    @Json(name = "permalink")
    var permalink: String? = null
    @Json(name = "url")
    var url: String? = null
    @Json(name = "id")
    var id: String? = null
    @PrimaryKey(autoGenerate = true)
    var key: Int? = null



    fun fullUrl() = "https://reddit.com$permalink"
}
