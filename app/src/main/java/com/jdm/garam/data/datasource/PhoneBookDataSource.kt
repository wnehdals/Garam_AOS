package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.phonebook.PhoneBookResp
import retrofit2.Response

interface PhoneBookDataSource {
    suspend fun getPhoneBookData(serviceKey: String): Response<PhoneBookResp>
}