package com.jdm.garam.util

import java.util.*

class DateUtil {
    fun getMaxDateOfMonth(month: Int): Int {
        val utc = TimeZone.getTimeZone("UTC")
        var calendar = Calendar.getInstance(utc)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return calendar.get(Calendar.DATE)
    }
    fun getCurrentMonth(): Int {
        val utc = TimeZone.getTimeZone("UTC")
        var calendar = Calendar.getInstance(utc)
        return calendar.get(Calendar.MONTH)
    }
    fun getCurrentYear(): Int {
        val utc = TimeZone.getTimeZone("UTC")
        var calendar = Calendar.getInstance(utc)
        return calendar.get(Calendar.YEAR)
    }
    fun getCurrentCalendar(): Calendar {
        val utc = TimeZone.getTimeZone("UTC")
        return Calendar.getInstance(utc)
    }
}