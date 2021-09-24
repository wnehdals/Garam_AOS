package com.jdm.garam.data.repository

import com.jdm.garam.data.datasource.CoronaDataSource
import com.jdm.garam.data.datasource.RemoteCoronaDataSource
import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.data.response.version.Version
import io.reactivex.rxjava3.core.Single

class CoronaRepositoryImpl(private val remoteCoronaDataSource: CoronaDataSource): CoronaRepository {
    override fun getCoronaStatistic(): Single<Result> {
        return Single.create { subscriber ->
            remoteCoronaDataSource.getCoronaStatistic()
                .subscribe({
                    subscriber.onSuccess(Result.Success(it))
                },{
                    subscriber.onSuccess(Result.Fail(CoronaStatistic()))
                })
        }
    }

    override fun getVersion(): Single<Result> {
        return Single.create{ subscriber ->
            remoteCoronaDataSource.getVersion()
                .subscribe({
                    if(it.statusCode == 200)
                        subscriber.onSuccess(Result.Success(it.body))
                    else
                        subscriber.onSuccess(Result.Fail(Version()))
                }, {
                    subscriber.onSuccess(Result.Fail(Version()))
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