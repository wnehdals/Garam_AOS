package com.jdm.garam.data.response.phonebook

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhoneBookResp(
    @SerializedName("statusCode")
    @Expose

    val currentCount: Int = 0,
    @SerializedName("matchCount")
    @Expose
    val matchCount: Int = 0,

    @SerializedName("page")
    @Expose
    val page: Int = 0,

    @SerializedName("perPage")
    @Expose
    val perPage: Int = 0,

    @SerializedName("totalCount")
    @Expose
    val totalCount: Int = 0,

    @SerializedName("data")
    @Expose
    val data: MutableList<PhoneBook> = mutableListOf(),
    )

data class PhoneBook(
    @SerializedName("일련번호")
    @Expose
    val id: Int = 0,

    @SerializedName("관공서명")
    @Expose
    val name: String = "",

    @SerializedName("데이터기준일자")
    @Expose
    val date: String = "",

    @SerializedName("전화번호")
    @Expose
    val phone: String = "",

    @SerializedName("주         소")
    @Expose
    val address: String = ""


)
