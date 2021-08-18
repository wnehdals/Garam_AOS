package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.CoronaStatistic
import io.reactivex.rxjava3.core.Single

class LocalCoronaDataSource: CoronaDataSource {
    override fun getCoronaStatistic(): Single<CoronaStatistic> {
        return Single.never()
    }
}