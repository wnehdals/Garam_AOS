package com.jdm.garam.ui.calendar.decorator
/*
import android.graphics.Color
import android.os.Build
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.annotation.RequiresApi
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.time.LocalDate
import java.util.*

class SunDayDecorator: DayViewDecorator {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        var year = day!!.year
        var month = day!!.month
        var day = day!!.day
        var dayOfWeek = LocalDate.of(year,month,day).dayOfWeek.value
        return dayOfWeek == Calendar.SUNDAY+6
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object : ForegroundColorSpan(Color.RED){})
    }
}

 */