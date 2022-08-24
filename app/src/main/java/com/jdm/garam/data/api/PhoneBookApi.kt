package com.jdm.garam.data.api

import com.jdm.garam.data.response.ResponseX
import com.jdm.garam.data.response.phonebook.PhoneBookResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhoneBookApi {
    @GET("api/15013640/v1/uddi:0a7d3f8a-e4b2-4556-8610-c19ec1d55e04")
    suspend fun getPhoneData(@Query("serviceKey") serviceKey: String, @Query("page") page: Int = 1, @Query("perPage") perPage: Int = 100, @Query("returnType") returnType:String = "JSON"): Response<PhoneBookResp>
}