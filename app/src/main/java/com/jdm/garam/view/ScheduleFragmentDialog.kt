package com.jdm.garam.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jdm.garam.R
import com.jdm.garam.data.response.schedule.Schedule
import com.jdm.garam.databinding.FragmentEventDialogBinding
import com.jdm.garam.ui.calendar.ScheduleAdapter

class ScheduleFragmentDialog(
    private var day: String,
    private var schedules: List<Schedule>,
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentEventDialogBinding
    private val scheduleAdapter = ScheduleAdapter()
    var itemClick: (Schedule) -> Unit = {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
        setStyle(STYLE_NO_TITLE, R.style.BaseBottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            scheduleAdapter.onClick = this@ScheduleFragmentDialog::dialogItemClick
            scheduleDialogRecyclerview.adapter = scheduleAdapter
            scheduleAdapter.submitList(schedules)
            scheduleDialogTitle.text = day
        }
    }
    fun dialogItemClick(item: Schedule) {
        itemClick(item)
    }

}