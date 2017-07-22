package com.nicholasdoglio.eyebleach.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Image {

    @SerializedName("source")
    @Expose
    var source: Source? = null
    @SerializedName("resolutions")
    @Expose
    var resolutions: List<Resolution>? = null
    @SerializedName("variants")
    @Expose
    var variants: Variants? = null
    @SerializedName("id")
    @Expose
    var id: String? = null

}
