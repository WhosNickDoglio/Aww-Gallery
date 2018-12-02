package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class Children(
    @Json(name = "kind") val kind: String, // t3
    @Json(name = "data") val data: DataX
)