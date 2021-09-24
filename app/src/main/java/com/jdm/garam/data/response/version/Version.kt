package com.jdm.garam.data.response.version

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Version(
    @SerializedName("version")
    @Expose
    val version: Float = 0f,
)
