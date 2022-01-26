package com.jdm.garam.ui.event.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.campaign.Event
import com.jdm.garam.databinding.ItemEventBigTextBinding

class EventBigTextViewHolder(private val binding: ItemEventBigTextBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Event) {
        binding.apply {
            bigText = item.content
            executePendingBindings()
        }
    }
}