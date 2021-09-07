package com.jdm.garam.data.api

import com.jdm.garam.data.response.bus.BusResp
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/default/Hello_Lamda")
    fun getBusData(): Single<BusResp>

}