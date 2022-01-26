package com.jdm.garam.ui.event.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.campaign.Event
import com.jdm.garam.databinding.ItemEventTextBinding

class EventTextViewHolder(private val binding: ItemEventTextBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Event) {
        binding.apply {
            text = item.content
            executePendingBindings()
        }
    }
}