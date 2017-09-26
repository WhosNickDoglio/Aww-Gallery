package com.nicholasdoglio.eyebleach.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import org.jetbrains.annotations.NotNull

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
    @PrimaryKey
    @NotNull
    @Json(name = "id")
    var id: String? = null
}
