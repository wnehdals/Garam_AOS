package com.jdm.garam.data.repository

import com.jdm.garam.data.datasource.BusDataSource
import com.jdm.garam.data.response.Bus
import io.reactivex.rxjava3.core.Single

class BusRepositoryImpl(private val remoteBusDataSource: BusDataSource): BusRepository {
    override fun getBusData(url: String): Single<Result> {
        return Single.create { subscriber ->
            remoteBusDataSource.getBusData(url)
                .subscribe({
                    subscriber.onSuccess(Result.Success(it))
                },{
                    subscriber.onSuccess(Result.Fail(Bus.EMPTYLIST))
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

        data class Error(
            val e: Throwable
        ) : Result()

    }
}