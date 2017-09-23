package com.nicholasdoglio.eyebleach.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import org.jetbrains.annotations.NotNull

@Entity
class ChildData() : Parcelable {
    @Json(name = "selftext")
    var selftext: String? = null
    @Json(name = "thumbnail")
    var thumbnail: String? = null
    @Json(name = "permalink")
    var permalink: String? = null
    @Json(name = "url")
    var url: String? = null
    @PrimaryKey
    @NotNull
    @Json(name = "id")
    var id: String? = null

    constructor(parcel: Parcel) : this() {
        selftext = parcel.readString()
        thumbnail = parcel.readString()
        permalink = parcel.readString()
        url = parcel.readString()
        id = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(selftext)
        parcel.writeString(thumbnail)
        parcel.writeString(permalink)
        parcel.writeString(url)
        parcel.writeString(id)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ChildData> {
        override fun createFromParcel(parcel: Parcel): ChildData = ChildData(parcel)

        override fun newArray(size: Int): Array<ChildData?> = arrayOfNulls(size)
    }
}
