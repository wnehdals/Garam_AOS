package com.jdm.garam.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jdm.garam.data.api.RealEstateApi
import com.jdm.garam.data.response.Building
import com.jdm.garam.util.YearUtil

class RealEstatePagingSource(private val api: RealEstateApi, private val serviceKey: String) :
    PagingSource<Int, Building>() {
    private lateinit var yearUtil: YearUtil

    init {
        yearUtil = YearUtil()
    }

    override fun getRefreshKey(state: PagingState<Int, Building>): Int? {
        return null
    }

    suspend fun getSHRData(temp: MutableList<Building>, nextPageNumber: Int) {
        try {
            val responseSH = api.getSHRentData(
                serviceKey = serviceKey,
                dealYMD = nextPageNumber as Int
            ).response.body.items.item
            temp.addAll(responseSH)
        } catch (e: Exception) {

        }
    }
    suspend fun getRHRData(temp: MutableList<Building>, nextPageNumber: Int) {
        try {
            val responseSH = api.getRHRentData(
                serviceKey = serviceKey,
                dealYMD = nextPageNumber as Int
            ).response.body.items.item
            temp.addAll(responseSH)
        } catch (e: Exception) {

        }
    }
    suspend fun getAPTRData(temp: MutableList<Building>, nextPageNumber: Int) {
        try {
            val responseSH = api.getAptRentData(
                serviceKey = serviceKey,
                dealYMD = nextPageNumber as Int
            ).response.body.items.item
            temp.addAll(responseSH)
        } catch (e: Exception) {

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Building> {
        val nextPageNumber = params.key ?: yearUtil.getCurrentKey()
        return try {
            var temp = mutableListOf<Building>()
            temp.clear()
            var timeline = Building().apply { type = nextPageNumber }
            temp.add(timeline)

            getSHRData(temp, nextPageNumber)
            getRHRData(temp, nextPageNumber)
            getAPTRData(temp, nextPageNumber)

            return LoadResult.Page(
                data = temp,
                prevKey = yearUtil.prevKey(nextPageNumber),
                nextKey = yearUtil.nextKey(nextPageNumber)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}