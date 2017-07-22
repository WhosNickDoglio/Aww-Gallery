package com.nicholasdoglio.eyebleach.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Child {

    @SerializedName("kind")
    @Expose
    var kind: String? = null
    @SerializedName("data")
    @Expose
    var data: Data_? = null

}
