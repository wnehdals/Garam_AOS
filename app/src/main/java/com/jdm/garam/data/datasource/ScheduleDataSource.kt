package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.schedule.ScheduleResp
import io.reactivex.rxjava3.core.Single

interface ScheduleDataSource {
    fun getScheduleData(month: String): Single<ScheduleResp>
}