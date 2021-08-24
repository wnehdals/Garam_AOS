package com.jdm.garam.data.repository

import com.jdm.garam.data.response.Bus
import io.reactivex.rxjava3.core.Single

interface BusRepository {
    fun getBusData(url: String): Single<BusRepositoryImpl.Result>
}