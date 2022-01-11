package com.jdm.garam.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.schedule.Schedule
import com.jdm.garam.databinding.ItemCalendarEventBinding

class ScheduleAdapter : ListAdapter<Schedule, ScheduleAdapter.ViewHolder>(scheduleDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCalendarEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule = getItem(position)
        if (schedule != null) {
            holder.bind(schedule)
        }
    }

    inner class ViewHolder(val binding: ItemCalendarEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Schedule) {
            with(binding) {
                eventTitle.text = item.title
                eventStartDate.text = item.date.toString()
                eventEndDate.text = item.endDate.toString()
                eventTime.text = item.time
            }
        }
    }

    companion object {
        val scheduleDiffUtil = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}