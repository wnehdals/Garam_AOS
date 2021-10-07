package com.jdm.garam.data.repository

import com.jdm.garam.data.response.Response
import io.reactivex.rxjava3.core.Single

interface RealEstateRepository {
    fun getRealEstateData(serviceKey: String, lawdCd: Int, dealYMD: Int): Single<RealEstateRepositoryImpl.Result>
}