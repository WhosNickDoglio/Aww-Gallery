package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class Data(
  @Json(name = "modhash") val modhash: String, // mxi6pjry8x86183929f41535c6997d6a8549a02a98900d0969
  @Json(name = "dist") val dist: Int, // 10
  @Json(name = "children") val children: List<Children>,
  @Json(name = "after") val after: String, // t3_95l20z
  @Json(name = "before") val before: Any? // null
)