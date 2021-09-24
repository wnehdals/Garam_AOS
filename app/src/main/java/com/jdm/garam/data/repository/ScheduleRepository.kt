package com.jdm.garam.data.repository

import io.reactivex.rxjava3.core.Single

interface ScheduleRepository {
    fun getScheduleData(month: String): Single<ScheduleRepositoryImpl.Result>
}