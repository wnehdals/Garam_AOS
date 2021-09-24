package com.jdm.garam.data.response.schedule

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleResp(
    @SerializedName("statusCode")
    @Expose
    val statusCode: Int = 0,
    @SerializedName("body")
    @Expose
    val body: List<Schedule> = listOf(),
)