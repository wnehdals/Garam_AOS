package com.jdm.garam.data.response

data class CoronaStatistic(
    var infected: String = "", //확진자
    var sum: String = "",      //누적
    var inspected: String = "",//자가격리중
    var selfQuarantine: String = ""//해제
)
