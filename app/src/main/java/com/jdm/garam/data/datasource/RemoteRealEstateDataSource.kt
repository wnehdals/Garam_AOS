package com.jdm.garam.data.datasource

import com.jdm.garam.data.api.Api
import com.jdm.garam.data.api.RealEstateApi
import com.jdm.garam.data.response.Response
import com.jdm.garam.data.response.ResponseX
import io.reactivex.rxjava3.core.Single

class RemoteRealEstateDataSource(private val api: RealEstateApi): RealEstateDataSource {
    override fun getRealEstateData(
        serviceKey: String,
        lawdCd: Int,
        dealYMD: Int
    ): Single<ResponseX> {
        val k = api.getRealEstateData(serviceKey, lawdCd, dealYMD)
        return k
    }
}