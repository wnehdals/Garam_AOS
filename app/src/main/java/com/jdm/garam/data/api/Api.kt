package com.jdm.garam.data.api

import com.jdm.garam.data.response.Response
import com.jdm.garam.data.response.schedule.ScheduleResp
import com.jdm.garam.data.response.bus.BusResp
import com.jdm.garam.data.response.coronastep.CoronaStepResp
import com.jdm.garam.data.response.version.VersionResp
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/prod/bus")
    fun getBusData(): Single<BusResp>

    @GET("/prod/schedule/{month}")
    fun getSchedule(@Path("month") month: String): Single<ScheduleResp>

    @GET("/prod/version")
    fun getVersion(): Single<VersionResp>

    @GET("/prod/corona/step")
    fun getCoronaStep(): Single<CoronaStepResp>


}