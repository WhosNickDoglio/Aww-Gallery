package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class Preview(
    @Json(name = "images") val images: List<Image>,
    @Json(name = "enabled") val enabled: Boolean // true
)