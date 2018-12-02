package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class Resolution(
    @Json(name = "url") val url: String, // https://i.redditmedia.com/ve8JWirKp-85ZKWYUleUF7oH16jpAjtMm8Oagyz_Ojg.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=1080&amp;s=5316b203fa6bef3b37915500d074b9fc
    @Json(name = "width") val width: Int, // 1080
    @Json(name = "height") val height: Int // 1440
)