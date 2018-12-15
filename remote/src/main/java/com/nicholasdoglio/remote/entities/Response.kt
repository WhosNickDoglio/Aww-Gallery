package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class Response(
  @Json(name = "kind") val kind: String, // Listing
  @Json(name = "data") val data: Data
)