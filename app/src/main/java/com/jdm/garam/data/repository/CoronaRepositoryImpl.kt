package com.jdm.garam.data.repository

import com.jdm.garam.data.datasource.CoronaDataSource
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

    override fun getVersion(onSuccess: (Version) -> Unit, onError: (String?) -> Unit) {
        val docRef = remoteCoronaDataSource.getVersion()!!
        docRef.get().addOnSuccessListener {
            if (it.data != null ) {
                val k = it.data
                val b = k?.get("versionCode")
                val versionCode: Long = (it.data!!["versionCode"] ?: 0) as Long
                val isForce: Boolean = (it.data!!["isFOrce"] ?: false) as Boolean
                onSuccess(Version(isForce = isForce, version = versionCode))
            }
        }.addOnFailureListener {
            onError(it.message)
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