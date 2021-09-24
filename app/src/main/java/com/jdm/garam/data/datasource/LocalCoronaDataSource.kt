package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.data.response.version.VersionResp
import io.reactivex.rxjava3.core.Single

class LocalCoronaDataSource: CoronaDataSource {
    override fun getCoronaStatistic(): Single<CoronaStatistic> {
        return Single.never()
    }

    override fun getVersion(): Single<VersionResp> {
        return Single.never()
    }
}