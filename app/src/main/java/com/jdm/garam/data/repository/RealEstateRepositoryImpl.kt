package com.jdm.garam.data.repository

import android.util.Log
import com.jdm.garam.data.datasource.RealEstateDataSource
import com.jdm.garam.data.datasource.RemoteRealEstateDataSource
import com.jdm.garam.data.response.Building
import io.reactivex.rxjava3.core.Single

class RealEstateRepositoryImpl(private val remoteRealEstateDataSource: RealEstateDataSource): RealEstateRepository {
    override fun getRealEstateData(serviceKey: String, lawdCd: Int, dealYMD: Int): Single<Result> {
        return Single.create{ subscriber ->
            remoteRealEstateDataSource.getRealEstateData(serviceKey, lawdCd, dealYMD)
                .subscribe({
                    if(it.response.header.resultCode == "00")
                        subscriber.onSuccess(Result.Success(it.response.body.items.item))
                    else
                        subscriber.onSuccess(Result.Fail(mutableListOf<Building>()))
                },{
                    subscriber.onSuccess(Result.Fail(mutableListOf<Building>()))
                })

        }
    }

    sealed class Result {

        data class Success<T>(
            val data: T? = null
        ) : Result()

        data class Fail<T>(
            val data: T? = null
        ) : Result()
    }
}