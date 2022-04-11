package com.jdm.garam.data.repository

import androidx.paging.PagingData
import com.jdm.garam.data.response.Building
import kotlinx.coroutines.flow.Flow

interface RealEstateRepository {
    fun getRealEstateData(serviceKey: String): Flow<PagingData<Building>>
}