package com.jdm.garam.data.datasource

import com.jdm.garam.data.api.PhoneBookApi
import com.jdm.garam.data.response.phonebook.PhoneBookResp
import retrofit2.Response

class RemotePhoneBookDataSource(private val api: PhoneBookApi): PhoneBookDataSource {
    override suspend fun getPhoneBookData(serviceKey: String): Response<PhoneBookResp> {
        return api.getPhoneData(serviceKey = serviceKey)
    }
}