package com.jdm.garam.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

data class ResponseX(
    @SerializedName("response")
    @Expose
    val response: Response = Response()
)
data class Response(
    @SerializedName("header")
    @Expose
    val header: Header = Header(),
    @SerializedName("body")
    @Expose
    val body: Body = Body()
)
data class Header(
    @SerializedName("resultCode")
    @Expose
    val resultCode: String = "unkown",

    @SerializedName("resultMsg")
    @Expose
    val resultMsg: String = "unkown"
)
data class Body(

    @SerializedName("items")
    @Expose
    val items: Items = Items(),
    @SerializedName("numOfRows")
    @Expose
    val numOfRows: Int = 0,
    @SerializedName("pageNo")
    @Expose
    val pageNo: Int = 0,
    @SerializedName("totalCount")
    @Expose
    val totalCount: Int = 0

)
data class Items(
    @SerializedName("item")
    @Expose
    val item: MutableList<Building> = mutableListOf()
)
data class Building(
    @SerializedName("건축년도")
    @Expose
    val buildCreateAt: Int = 0,
    @SerializedName("년")
    @Expose
    val dealYear: Int = 0,
    @SerializedName("법정동")
    @Expose
    val dong: String = "",
    @SerializedName("보증금액")
    @Expose
    val deposit: String = "비공개",
    @SerializedName("아파트")
    @Expose
    val apartment: String = "",
    @SerializedName("월")
    @Expose
    val dealMonth: Int = 0,
    @SerializedName("월세금액")
    @Expose
    val monthlyRent: String = "",
    @SerializedName("일")
    @Expose
    val dealDay: Int = 0,
    @SerializedName("전용면적")
    @Expose
    val areaForExclusiveUse: Float = 0f,
    @SerializedName("층")
    @Expose
    val floor: Int = 0,
    var type: Int = 0

) {
    fun getDate(): String {
        return "${dealYear}.${dealMonth}.${dealDay}"
    }
}




