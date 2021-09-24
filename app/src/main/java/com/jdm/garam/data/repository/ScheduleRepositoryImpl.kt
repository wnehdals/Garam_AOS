package com.jdm.garam.data.repository

import com.jdm.garam.data.datasource.ScheduleDataSource
import com.jdm.garam.data.response.schedule.Schedule
import io.reactivex.rxjava3.core.Single

class ScheduleRepositoryImpl(private val remoteScheduleDatasource: ScheduleDataSource ): ScheduleRepository {
    override fun getScheduleData(month: String): Single<Result> {
        return Single.create{ subscriber ->
            remoteScheduleDatasource.getScheduleData(month)
                .subscribe({
                    if(it.statusCode == 200)
                        subscriber.onSuccess(Result.Success(it.body))
                    else
                        subscriber.onSuccess(Result.Fail(listOf<Schedule>()))
                }, {
                    subscriber.onSuccess(Result.Fail(listOf<Schedule>()))
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