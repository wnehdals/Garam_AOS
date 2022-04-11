package com.jdm.garam.data.datasource

import androidx.paging.PagingData
import com.jdm.garam.data.response.Building
import kotlinx.coroutines.flow.Flow

interface RealEstateDataSource {
    fun getRealEstateData(serviceKey: String): Flow<PagingData<Building>>
}