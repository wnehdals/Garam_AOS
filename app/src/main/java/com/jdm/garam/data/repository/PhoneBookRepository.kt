package com.jdm.garam.data.repository

import com.jdm.garam.data.response.phonebook.PhoneBook
import com.jdm.garam.data.response.phonebook.PhoneBookResp
import retrofit2.Response

interface PhoneBookRepository {
    suspend fun getPhoneBookData(serviceKey: String, onSuccess: (MutableList<PhoneBook>) -> Unit, onError: (String) -> Unit)
}