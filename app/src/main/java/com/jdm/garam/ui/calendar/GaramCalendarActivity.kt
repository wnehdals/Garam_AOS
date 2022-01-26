package com.jdm.garam.ui.calendar

import android.content.Intent
import android.util.Log
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.schedule.Schedule
import com.jdm.garam.databinding.ActivityGaramCalendarBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.event.EventActivity
import com.jdm.garam.util.CAMPAIGN
import com.jdm.garam.util.DateUtil
import com.jdm.garam.view.ScheduleFragmentDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class GaramCalendarActivity : ViewBindingActivity<ActivityGaramCalendarBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_garam_calendar
    private val viewModel: ScheduleViewModel by viewModel()
    private val dateUtil = DateUtil()
    override fun subscribe() {
        viewModel.scheduleState.observe(this, {
            when (it) {
                is BaseState.Loading -> {
                    showProgressDialog()
                }
                is BaseState.Success<*> -> {
                    val list = it.SuccessResp as List<Schedule>
                    setEventFromCalendar(list)
                    hideProgressDialog()
                }
                is BaseState.Fail<*> -> {
                    hideProgressDialog()
                    showFailToastMessage()
                }
            }
        })

    }

    private fun setEventFromCalendar(list: List<Schedule>) {
        var maxDateOfMonth = dateUtil.getMaxDateOfMonth(viewModel.currentMonth)
        for (i in list) {
            for (j in 0..maxDateOfMonth) {
                if (i.date == j) {
                    var calendar = Calendar.getInstance()
                    var year = binding.calendarView.currentPageDate.get(Calendar.YEAR)
                    calendar.set(year, viewModel.currentMonth, j)
                    viewModel.events.add(EventDay(calendar, R.drawable.ic_event_note))
                }
            }
        }
        binding.calendarView.setEvents(viewModel.events)
    }

    private fun initCalendar() {
        val calendarMin = Calendar.getInstance()
        calendarMin.add(Calendar.DAY_OF_MONTH, -30)
        val calendarMax = Calendar.getInstance()
        calendarMax.add(Calendar.DAY_OF_MONTH, +30)
        binding.calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay?) {
                eventDay?.let {
                    var month = it.calendar.get(Calendar.MONTH)
                    var day = it.calendar.get(Calendar.DATE)
                    var schedules = viewModel.getDayEvent(month + 1, day)
                    ScheduleFragmentDialog(
                        "${month + 1}" + getString(R.string.month) + " ${day}" + getString(
                            R.string.day
                        ), schedules
                    ).apply { itemClick = this@GaramCalendarActivity::onClickScheduleItem }
                        .show(supportFragmentManager, TAG)
                }


            }

        })
        with(binding) {
            calendarView.setMinimumDate(calendarMin)
            calendarView.setMaximumDate(calendarMax)
            calendarView.setOnForwardPageChangeListener {
                viewModel.currentMonth += 1
                if (Calendar.DECEMBER < viewModel.currentMonth) {
                    viewModel.currentMonth = Calendar.JANUARY
                }
                viewModel.getScheduleData("${viewModel.currentMonth + 1}")
            }
            calendarView.setOnPreviousPageChangeListener {
                viewModel.currentMonth -= 1
                Log.e("month", viewModel.currentMonth.toString())
                if (Calendar.JANUARY > viewModel.currentMonth) {
                    viewModel.currentMonth = Calendar.DECEMBER
                }
                viewModel.getScheduleData("${viewModel.currentMonth + 1}")
            }
        }
    }

    override fun initView() {
        setBaseAppBar(getString(R.string.schedule))
        setBackKey()
        viewModel.currentMonth = dateUtil.getCurrentMonth()
        viewModel.getScheduleData("${viewModel.currentMonth + 1}")

        initCalendar()
    }
    private fun onClickScheduleItem(item: Schedule) {
        if (item.campaignId.isNotEmpty()) {
            Intent(this, EventActivity::class.java)
                .putExtra(CAMPAIGN, item.campaignId)
                .run { startActivity(this) }
        }
    }

       companion object {
        private val TAG = GaramCalendarActivity::class.java.simpleName
    }
}