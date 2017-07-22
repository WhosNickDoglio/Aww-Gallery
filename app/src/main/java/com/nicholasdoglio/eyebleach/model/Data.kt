package com.nicholasdoglio.eyebleach.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("modhash")
    @Expose
    var modhash: String? = null
    @SerializedName("children")
    @Expose
    var children: List<Child>? = null
    @SerializedName("after")
    @Expose
    var after: String? = null
    @SerializedName("before")
    @Expose
    var before: Any? = null

}
