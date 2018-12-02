package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class Source(
    @Json(name = "url") val url: String, // https://i.redditmedia.com/ve8JWirKp-85ZKWYUleUF7oH16jpAjtMm8Oagyz_Ojg.jpg?s=717e4f1bd3906913e6dfc086b816a740
    @Json(name = "width") val width: Int, // 3024
    @Json(name = "height") val height: Int // 4032
)