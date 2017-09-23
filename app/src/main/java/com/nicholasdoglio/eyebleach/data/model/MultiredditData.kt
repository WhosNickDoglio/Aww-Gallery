package com.nicholasdoglio.eyebleach.data.model

import com.squareup.moshi.Json

class MultiredditData {
    @Json(name = "modhash")
    var modhash: String? = null
    @Json(name = "children")
    var children: List<Child>? = null
    @Json(name = "after")
    var after: String? = null
}
