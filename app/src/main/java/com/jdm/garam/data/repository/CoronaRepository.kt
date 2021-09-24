package com.jdm.garam.data.repository

import com.jdm.garam.data.response.CoronaStatistic
import io.reactivex.rxjava3.core.Single

interface CoronaRepository {
    fun getCoronaStatistic(): Single<CoronaRepositoryImpl.Result>
    fun getVersion(): Single<CoronaRepositoryImpl.Result>
}