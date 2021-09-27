package com.jdm.garam.data.response.coronastep

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jdm.garam.data.response.schedule.Schedule

data class CoronaStepResp(
    @SerializedName("statusCode")
    @Expose
    val statusCode: Int = 0,
    @SerializedName("body")
    @Expose
    val body: CoronaStep = CoronaStep()
)
