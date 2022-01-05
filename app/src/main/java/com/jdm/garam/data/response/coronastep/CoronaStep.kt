package com.jdm.garam.data.response.coronastep

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jdm.garam.data.response.schedule.Schedule

data class CoronaStep(
    @SerializedName("duration")
    @Expose
    var duration: String = "",
    @SerializedName("step")
    @Expose
    var step: String = ""
)
