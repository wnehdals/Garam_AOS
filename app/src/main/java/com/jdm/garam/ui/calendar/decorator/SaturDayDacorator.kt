package com.jdm.garam.ui.calendar.decorator
/*
import android.graphics.Color
import android.os.Build
import android.text.style.ForegroundColorSpan
import androidx.annotation.RequiresApi
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.time.LocalDate
import java.util.*

class SaturDayDacorator : DayViewDecorator {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        var year = day!!.year
        var month = day!!.month
        var day = day!!.day
        var dayOfWeek = LocalDate.of(year,month,day).dayOfWeek.value
        return dayOfWeek == Calendar.SATURDAY-1
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object : ForegroundColorSpan(Color.BLUE){})
    }
}

 */