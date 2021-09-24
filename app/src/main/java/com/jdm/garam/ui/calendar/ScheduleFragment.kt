package com.jdm.garam.ui.calendar

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.material.composethemeadapter.MdcTheme
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.data.response.schedule.Schedule
import com.jdm.garam.databinding.FragmentScheduleBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.util.ARG_DATE
import com.jdm.garam.util.ARG_DAY
import com.jdm.garam.util.GRAY
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel
class ScheduleFragment : ViewBindingFragment<FragmentScheduleBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_schedule
    private val viewModel: ScheduleViewModel by viewModel()

    override fun subscribe() {
        viewModel.scheduleState.observe(viewLifecycleOwner, {
            when(it) {
                is BaseState.Success<*> -> {
                    var result = it.SuccessResp as List<Schedule>
                    if(!result.isEmpty()) {
                        Collections.sort(result)
                        setSchedule(result)

                    }
                        
                }
                is BaseState.Fail<*> -> {
                    var result = it.FailResp as List<Schedule>

                }
            }
        })
    }

    private var param: Int? = null
    private var date: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                    requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getInt(ARG_DAY)
            date = it.getString(ARG_DATE)
        }
    }

    override fun initView() {

        val utc = TimeZone.getTimeZone("UTC")
        val currentDate = Calendar.getInstance(utc)
        Log.e("year", currentDate.get(Calendar.YEAR).toString())
        var month = date?.split("\n")?.get(0)
        var day = date?.split("\n")?.get(1)
        Log.e("date", currentDate.get(Calendar.DATE).toString())
        Log.e("day", currentDate.get(Calendar.DAY_OF_WEEK).toString())
        Log.e("dayofmonth", currentDate.getActualMaximum(Calendar.DAY_OF_MONTH).toString())
        viewModel.getScheduleData("${month}a${day}")


    }
    fun setSchedule(list: List<Schedule>) {
        binding.scheduleComposeview.setContent {
            MdcTheme {
                drawRecyclerView(list)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param: Int, date: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DAY, param)
                    putString(ARG_DATE, date)
                }
            }
    }
}
@Composable
private fun drawRecyclerView(scheduleList: List<Schedule>) {
    LazyColumn {
        items(scheduleList) { schedule ->
            drawScheduleItem(schedule = schedule)
        }
    }
}
@Composable
private fun drawScheduleItem(schedule: Schedule) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)) {
        val (title, month, startDate, endDate, place, time, divide) = createRefs()
        Text(text = schedule.time, Modifier.constrainAs(time) {
            top.linkTo(parent.top, 16.dp)
            start.linkTo(parent.start, 16.dp)
        }, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = schedule.title, Modifier.constrainAs(title) {
            top.linkTo(time.bottom, 8.dp)
            start.linkTo(parent.start, 16.dp)
        }, fontSize = 12.sp)
        Text(text = "장소 : ${schedule.place}", Modifier.constrainAs(place){
            top.linkTo(title.bottom, 8.dp)
            start.linkTo(parent.start, 16.dp)
        }, fontSize = 12.sp)
        Divider(color = GRAY, modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .constrainAs(divide) {
                top.linkTo(place.bottom, 8.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
            })
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MdcTheme {

    }
}