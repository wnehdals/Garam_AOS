package com.jdm.garam.data.response.bus

data class BusType(
    val type: Int,
    val id: String = "",
    val description: String = "",
    val stations: String = "",
    val startStation: String = "",
    val endStation: String = "",
) {
}