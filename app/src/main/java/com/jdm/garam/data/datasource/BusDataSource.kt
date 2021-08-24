package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.Bus
import com.jdm.garam.data.response.CoronaStatistic
import io.reactivex.rxjava3.core.Single

interface BusDataSource {
    fun getBusData(url: String): Single<MutableList<Bus>>
}