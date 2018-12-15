package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class Image(
  @Json(name = "source") val source: Source,
  @Json(name = "resolutions") val resolutions: List<Resolution>,
  @Json(name = "variants") val variants: Variants,
  @Json(name = "id") val id: String // PY4ZzYSGatREt9dMgLav0_eqy4cn1z5bAx4Z3RSt7is
)