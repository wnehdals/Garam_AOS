package com.jdm.garam.data.repository

import io.reactivex.rxjava3.core.Single

interface CoronaRepository {
    fun getCoronaStatistic(): Single<CoronaRepositoryImpl.Result>
    fun getVersion(): Single<CoronaRepositoryImpl.Result>
}