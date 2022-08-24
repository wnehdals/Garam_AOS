package com.jdm.garam.data.repository

import com.jdm.garam.data.datasource.PhoneBookDataSource
import com.jdm.garam.data.response.phonebook.PhoneBook

class PhoneBookRepositoryImpl(private val remotePhoneDataSource: PhoneBookDataSource): PhoneBookRepository {
    override suspend fun getPhoneBookData(
        serviceKey: String,
        onSuccess: (MutableList<PhoneBook>) -> Unit,
        onError: (String) -> Unit
    ) {
        val result = remotePhoneDataSource.getPhoneBookData(serviceKey)
        if (result.isSuccessful) {
            onSuccess(result.body()!!.data)
        } else {
            onError("")
        }
    }
}