package com.jdm.garam.data.repository

import com.jdm.garam.data.response.version.Version
import io.reactivex.rxjava3.core.Single

interface CoronaRepository {
    fun getCoronaStatistic(): Single<CoronaRepositoryImpl.Result>
    fun getVersion(onSuccess: (Version) -> Unit, onError: (String?) -> Unit)
}