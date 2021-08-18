package com.jdm.garam.data.response

data class CoronaStatistic(
    var infected: String = "", //확진자
    var cured: String = "",    //완치자
    var sum: String = "",      //누적
    var inspected: String = "",//검사중
    var negative: String = "", //음성판정
    var selfQuarantine: String = "",//자가격리
    var type: Int = 1
)
