package com.jdm.garam.data.response.version

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Version(
    var version: Long = 0L,
    var isForce: Boolean = false
)
