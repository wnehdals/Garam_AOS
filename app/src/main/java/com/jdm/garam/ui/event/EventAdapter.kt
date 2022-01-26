package com.jdm.garam.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.campaign.Event
import com.jdm.garam.databinding.ItemEventBigTextBinding
import com.jdm.garam.databinding.ItemEventImgBinding
import com.jdm.garam.databinding.ItemEventTextBinding
import com.jdm.garam.ui.event.viewholder.EventBigTextViewHolder
import com.jdm.garam.ui.event.viewholder.EventImgViewHolder
import com.jdm.garam.ui.event.viewholder.EventTextViewHolder
import com.jdm.garam.util.EVENT_TYPE_BIG_TEXT
import com.jdm.garam.util.EVENT_TYPE_IMG
import com.jdm.garam.util.EVENT_TYPE_TEXT

class EventAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val eventList = mutableListOf<Event>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EVENT_TYPE_BIG_TEXT -> EventBigTextViewHolder(ItemEventBigTextBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            EVENT_TYPE_TEXT -> EventTextViewHolder(ItemEventTextBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            EVENT_TYPE_IMG -> EventImgViewHolder(ItemEventImgBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            else -> EventBigTextViewHolder(ItemEventBigTextBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val event = eventList.get(position)
        if (event != null) {
            when (holder) {
                is EventBigTextViewHolder -> holder.bind(event)
                is EventTextViewHolder -> holder.bind(event)
                is EventImgViewHolder -> holder.bind(event)
            }
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun getItemViewType(position: Int): Int {
        var event = eventList.get(position)
        return when (event.type) {
            EVENT_TYPE_BIG_TEXT -> EVENT_TYPE_BIG_TEXT
            EVENT_TYPE_TEXT -> EVENT_TYPE_TEXT
            EVENT_TYPE_IMG -> EVENT_TYPE_IMG
            else -> EVENT_TYPE_BIG_TEXT
        }
    }
    fun addData(data: MutableList<Event>) {
        eventList.clear()
        eventList.addAll(data)
        notifyDataSetChanged()
    }
}