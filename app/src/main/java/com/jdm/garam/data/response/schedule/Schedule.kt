package com.jdm.garam.data.response.schedule

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    @SerializedName("id")
    @Expose
    var id: Int = -1,
    @SerializedName("month")
    @Expose
    var month: Int = -1,
    @SerializedName("date")
    @Expose
    var date: Int = -1,
    @SerializedName("desc")
    @Expose
    var desc: String = "",
    @SerializedName("img")
    @Expose
    var img: String = "",
    @SerializedName("owner")
    @Expose
    var owner: String = "",
    @SerializedName("place")
    @Expose
    var place: String = "",
    @SerializedName("time")
    @Expose
    var time: String = "",
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("endDate")
    @Expose
    var endDate: Int = 0

) : Parcelable, Comparable<Schedule> {
    override fun compareTo(other: Schedule): Int {
        var t = this.time.split(":")
        var ot = other.time.split(":")
        if(t[0].toInt() > ot[0].toInt()){
            return 1
        } else if(t[0].toInt() == ot[0].toInt()) {
            if(t[1].toInt() > ot[1].toInt()) {
                return 1
            } else if(t[1].toInt() == ot[1].toInt()) {
                return 0
            } else
                return -1
        } else {
            return -1
        }
    }
}

