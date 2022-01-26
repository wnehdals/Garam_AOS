package com.jdm.garam.data.response.campaign

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CampaignResp(
    @SerializedName("statusCode")
    @Expose
    val statusCode: Int = 0,
    @SerializedName("body")
    @Expose
    val body: MutableList<Event> = mutableListOf(),
)
