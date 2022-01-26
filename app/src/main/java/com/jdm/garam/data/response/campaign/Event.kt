package com.jdm.garam.data.response.campaign

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("type")
    @Expose
    val type: Int,

    @SerializedName("content")
    @Expose
    val content: String,
)
