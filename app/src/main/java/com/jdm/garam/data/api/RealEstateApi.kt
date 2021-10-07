package com.jdm.garam.data.api

import com.jdm.garam.data.response.Response
import com.jdm.garam.data.response.ResponseX
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RealEstateApi {
    @GET("/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent")
    fun getRealEstateData(@Query("serviceKey") serviceKey: String, @Query("LAWD_CD") lawdCd: Int, @Query("DEAL_YMD") dealYMD: Int, @Query("_type") type:String = "json"): Single<ResponseX>

}