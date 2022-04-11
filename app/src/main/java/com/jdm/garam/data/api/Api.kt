package com.jdm.garam.data.api

import com.jdm.garam.data.response.bus.BusResp
import com.jdm.garam.data.response.campaign.CampaignResp
import com.jdm.garam.data.response.schedule.ScheduleResp
import com.jdm.garam.data.response.version.VersionResp
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/prod/bus")
    fun getBusData(): Single<BusResp>

    @GET("/prod/schedule/{month}")
    fun getSchedule(@Path("month") month: String): Single<ScheduleResp>

    @GET("/prod/version")
    fun getVersion(): Single<VersionResp>


    @GET("/prod/event/{campaign}")
    fun getCampaign(@Path("campaign") campaignId: String): Single<CampaignResp>
}