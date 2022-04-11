package com.jdm.garam.data.repository

import androidx.paging.PagingData
import com.jdm.garam.data.datasource.RealEstateDataSource
import com.jdm.garam.data.response.Building
import kotlinx.coroutines.flow.Flow

class RealEstateRepositoryImpl(private val remoteRealEstateDataSource: RealEstateDataSource): RealEstateRepository {
    override fun getRealEstateData(serviceKey: String):  Flow<PagingData<Building>> {
       return remoteRealEstateDataSource.getRealEstateData(serviceKey)
    }
}