package com.jdm.garam.data.repository

import io.reactivex.rxjava3.core.Single

interface BusRepository {
    fun getBusData(type: Int): Single<BusRepositoryImpl.Result>
}