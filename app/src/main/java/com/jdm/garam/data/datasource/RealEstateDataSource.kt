package com.jdm.garam.data.datasource

import androidx.paging.PagingData
import com.jdm.garam.data.response.Building
import com.jdm.garam.data.response.Response
import com.jdm.garam.data.response.ResponseX
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface RealEstateDataSource {
    fun getRealEstateData(serviceKey: String): Flow<PagingData<Building>>
}