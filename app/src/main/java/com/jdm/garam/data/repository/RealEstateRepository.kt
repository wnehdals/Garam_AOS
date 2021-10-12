package com.jdm.garam.data.repository

import androidx.paging.PagingData
import com.jdm.garam.data.response.Building
import com.jdm.garam.data.response.Response
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface RealEstateRepository {
    fun getRealEstateData(serviceKey: String): Flow<PagingData<Building>>
}