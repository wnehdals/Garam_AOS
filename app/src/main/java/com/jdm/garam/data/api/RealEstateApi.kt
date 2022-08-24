package com.jdm.garam.data.api

import com.jdm.garam.data.response.ResponseX
import retrofit2.http.GET
import retrofit2.http.Query

interface RealEstateApi {
    @GET("/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcSHRent")
    suspend fun getSHRentData(@Query("serviceKey") serviceKey: String, @Query("LAWD_CD") lawdCd: Int = 42230, @Query("DEAL_YMD") dealYMD: Int, @Query("_type") type:String = "json"): ResponseX

    @GET("/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent")
    suspend fun getRHRentData(@Query("serviceKey") serviceKey: String, @Query("LAWD_CD") lawdCd: Int = 42230, @Query("DEAL_YMD") dealYMD: Int, @Query("_type") type:String = "json"): ResponseX


    @GET("/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent")
    suspend fun getAptRentData(@Query("serviceKey") serviceKey: String, @Query("LAWD_CD") lawdCd: Int = 42230, @Query("DEAL_YMD") dealYMD: Int, @Query("_type") type:String = "json"): ResponseX


}