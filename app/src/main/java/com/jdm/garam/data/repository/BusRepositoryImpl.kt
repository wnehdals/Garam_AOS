package com.jdm.garam.data.repository

import com.jdm.garam.data.datasource.BusDataSource
import com.jdm.garam.data.response.bus.Bus
import com.jdm.garam.data.response.bus.BusResp
import io.reactivex.rxjava3.core.Single

class BusRepositoryImpl(private val remoteBusDataSource: BusDataSource): BusRepository {
    override fun getBusData(type: Int): Single<Result> {
        return Single.create { subscriber ->
            remoteBusDataSource.getBusData()
                .map { typeFilter(it.busList, type) }
                .subscribe({
                    subscriber.onSuccess(Result.Success(it))
                },{
                    subscriber.onSuccess(Result.Fail(BusResp()))
                })
        }
    }
    fun typeFilter(list: MutableList<Bus>, type: Int): MutableList<Bus> {
        val filteredList = mutableListOf<Bus>()
        for(i in list.indices) {
            if(list[i].id >= type && list[i].id <= type + 10) {
                filteredList.add(list[i])
            }
        }
        return filteredList
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