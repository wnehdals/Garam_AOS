package com.jdm.garam.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bus(
    val id: String = "",
    val description: String = "",
    val stations: MutableList<String> = mutableListOf(),
    val startStation: String = "",
    val startStationTime: String = "",
    val endStation: String = "",
    val endStationTime: String= ""
): Parcelable {
    companion object{
        val EMPTYLIST = mutableListOf<Bus>()
    }
}
