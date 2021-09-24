package com.jdm.garam.data.datasource

import com.jdm.garam.data.api.Api
import com.jdm.garam.data.response.schedule.ScheduleResp
import io.reactivex.rxjava3.core.Single

class RemoteScheduleDataSource(private val api: Api): ScheduleDataSource {
    override fun getScheduleData(month: String): Single<ScheduleResp> {
        return api.getSchedule(month)
    }
}