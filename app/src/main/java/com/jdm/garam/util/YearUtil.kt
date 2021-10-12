package com.jdm.garam.util

import java.text.DecimalFormat
import java.util.*

class YearUtil {
    private var year = 2021
    private var month = 10
    private var basic = 202110
    private val df = DecimalFormat("00")
    init {
        val currentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        year = currentDate.get(Calendar.YEAR)
        month = currentDate.get(Calendar.MONTH).plus(1)
        basic = "${year}${df.format(month)}".toInt()
    }
    fun getCurrentKey(): Int {
        return "${year}${df.format(month)}".toInt()
    }
    fun nextKey(key: Int): Int {
        val s = key.toString()
        var year = s.substring(0,4).toInt()
        var month = s.substring(4,s.length).toInt()
        month -= 1
        if(month == 0) {
            year -=1
            month = 12
        }
        return "${year}${df.format(month)}".toInt()

    }
    fun prevKey(key: Int): Int? {
        if(key == basic) {
            return null
        } else {
            val s = key.toString()
            var year = s.substring(0,4).toInt()
            var month = s.substring(4,s.length).toInt()
            month += 1
            if(month == 13) {
                year +=1
                month = 1
            }
            return "${year}${df.format(month)}".toInt()
        }
    }
}