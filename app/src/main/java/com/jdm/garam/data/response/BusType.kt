package com.jdm.garam.data.response

data class BusType(
    val type: Int,
    val id: String = "",
    val description: String = "",
    val stations: String = "",
    val startStation: String = "",
    val startStationTime: String = "",
    val endStation: String = "",
    val endStationTime: String= ""
) {
}