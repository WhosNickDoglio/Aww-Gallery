package com.nicholasdoglio.eyebleach.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Preview {

    @SerializedName("images")
    @Expose
    var images: List<Image>? = null
    @SerializedName("enabled")
    @Expose
    var enabled: Boolean? = null

}
