package com.jdm.garam.data.response.bus

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BusResp(
    @SerializedName("Bus_List")
    @Expose
    val busList: MutableList<Bus> = mutableListOf<Bus>()
    )
