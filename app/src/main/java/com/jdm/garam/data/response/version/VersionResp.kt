package com.jdm.garam.data.response.version

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VersionResp(
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int = 0,
    @SerializedName("body")
    @Expose
    var body: Version = Version()
)
