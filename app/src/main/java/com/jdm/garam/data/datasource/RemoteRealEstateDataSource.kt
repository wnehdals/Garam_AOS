package com.jdm.garam.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdm.garam.data.api.RealEstateApi
import com.jdm.garam.data.response.Building
import kotlinx.coroutines.flow.Flow

class RemoteRealEstateDataSource(private val api: RealEstateApi) : RealEstateDataSource {
    override fun getRealEstateData(serviceKey: String): Flow<PagingData<Building>> {
        return Pager(PagingConfig(pageSize = 40)) {
            RealEstatePagingSource(api, serviceKey)
        }.flow

    }
}