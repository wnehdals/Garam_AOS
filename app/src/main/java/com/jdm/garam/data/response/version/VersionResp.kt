package com.jdm.garam.data.response.version

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VersionResp(
    @SerializedName("statusCode")
    @Expose
    val statusCode: Int = 0,
    @SerializedName("body")
    @Expose
    val body: Version = Version()
)
