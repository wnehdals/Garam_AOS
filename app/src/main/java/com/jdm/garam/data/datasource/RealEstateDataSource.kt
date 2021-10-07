package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.Response
import com.jdm.garam.data.response.ResponseX
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface RealEstateDataSource {
    fun getRealEstateData(serviceKey: String, lawdCd: Int, dealYMD: Int): Single<ResponseX>
}