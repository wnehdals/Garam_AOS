package com.jdm.garam.data.datasource

import com.google.firebase.firestore.DocumentReference
import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.data.response.version.VersionResp
import io.reactivex.rxjava3.core.Single

interface CoronaDataSource {
    fun getCoronaStatistic(): Single<CoronaStatistic>
    fun getVersion(): DocumentReference?
}