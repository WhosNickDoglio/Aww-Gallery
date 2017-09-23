package com.nicholasdoglio.eyebleach.data.model


import com.squareup.moshi.Json

class Multireddit {
    @Json(name = "kind")
    var kind: String? = null
    @Json(name = "data")
    var data: MultiredditData? = null
}
