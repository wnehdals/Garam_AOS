package com.jdm.garam.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdm.garam.data.datasource.RealEstateDataSource
import com.jdm.garam.data.datasource.RealEstatePagingSource
import com.jdm.garam.data.datasource.RemoteRealEstateDataSource
import com.jdm.garam.data.response.Building
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class RealEstateRepositoryImpl(private val remoteRealEstateDataSource: RealEstateDataSource): RealEstateRepository {
    override fun getRealEstateData(serviceKey: String):  Flow<PagingData<Building>> {
       return remoteRealEstateDataSource.getRealEstateData(serviceKey)
    }
}