package com.jdm.garam.ui.calendar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private var dayList = mutableListOf<String>()
    fun setDate(list:MutableList<String>) {
        dayList.addAll(list)
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        return ScheduleFragment.newInstance(position, dayList[position])
    }

}