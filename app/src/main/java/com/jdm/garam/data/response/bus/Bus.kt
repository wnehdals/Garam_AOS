package com.jdm.garam.data.response.bus

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bus(
    @SerializedName("id")
    @Expose
    val id: Int = -1,
    @SerializedName("number")
    @Expose
    val number: String = "unkown",
    @SerializedName("title")
    @Expose
    val title: String = "unkown",
    @SerializedName("startTime")
    @Expose
    val startTime: String = "unkown",
    @SerializedName("endTime")
    @Expose
    val endTime: String = "unkown",
    @SerializedName("route")
    @Expose
    val route: List<String> = listOf(),
): Parcelable {

}
