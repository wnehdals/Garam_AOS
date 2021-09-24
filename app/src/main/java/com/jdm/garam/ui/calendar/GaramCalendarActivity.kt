package com.jdm.garam.ui.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.databinding.ActivityGaramCalendarBinding
import java.util.*

class GaramCalendarActivity : ViewBindingActivity<ActivityGaramCalendarBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_garam_calendar
    private val days: Array<String> by lazy {
        resources.getStringArray(R.array.day_array)
    }
    private val viewpagerAdapter by lazy {
        CalendarPagerAdapter(this)
    }
    override fun subscribe() {
    }



    override fun initView() {
        setBaseAppBar(getString(R.string.schedule))
        setBackKey()
        initDate()

    }
    private fun initDate() {
        val utc = TimeZone.getTimeZone("UTC")
        val currentDate = Calendar.getInstance(utc)
        binding.calendarYearTextview.text = "${currentDate.get(Calendar.YEAR)}.${currentDate.get(Calendar.MONTH).plus(1)}"
        setDate(currentDate)
        Log.e("dayofmonth", currentDate.getActualMaximum(Calendar.DAY_OF_MONTH).toString())
        binding.calendarViewpager.setCurrentItem(currentDate.get(Calendar.DAY_OF_WEEK).minus(1))

    }
    private fun setDate(currentDate: Calendar) {
        var list = mutableListOf<String>()
        var dateList = mutableListOf<String>()
        var basic = currentDate.get(Calendar.DAY_OF_WEEK)
        for(i in (basic -1) downTo 1) {
            val utc = TimeZone.getTimeZone("UTC")
            val currentDate = Calendar.getInstance(utc)
            currentDate.add(Calendar.DAY_OF_WEEK, -i)
            var month = currentDate.get(Calendar.MONTH).plus(1)
            var date = currentDate.get(Calendar.DATE).toString()
            var day = getDay(currentDate.get(Calendar.DAY_OF_WEEK))
            list.add("${date}\n${day}")
            dateList.add("${month}\n${date}")
        }
        val utc = TimeZone.getTimeZone("UTC")
        val currentDate = Calendar.getInstance(utc)
        var date = currentDate.get(Calendar.DATE).toString()
        var day = getDay(currentDate.get(Calendar.DAY_OF_WEEK))
        var month = currentDate.get(Calendar.MONTH).plus(1)
        list.add("${date}\n${day}")
        dateList.add("${month}\n${date}")
        for(i in 1..(7-basic)) {
            val utc = TimeZone.getTimeZone("UTC")
            val currentDate = Calendar.getInstance(utc)
            currentDate.add(Calendar.DAY_OF_WEEK, i)
            var month = currentDate.get(Calendar.MONTH).plus(1)
            var date = currentDate.get(Calendar.DATE).toString()
            var day = getDay(currentDate.get(Calendar.DAY_OF_WEEK))
            list.add("${date}\n${day}")
            dateList.add("${month}\n${date}")
        }
        initTab(list, dateList)
    }
    private fun getDay(id: Int): String {
        return days[id-1]
    }
    private fun initTab(list: MutableList<String>, dateList: MutableList<String>) {
        binding.calendarViewpager.adapter =  CalendarPagerAdapter(this).apply { setDate(dateList) }
        TabLayoutMediator(binding.calendarTablayout, binding.calendarViewpager) { tab, position ->
            tab.text = list[position]
        }.attach()
    }
}