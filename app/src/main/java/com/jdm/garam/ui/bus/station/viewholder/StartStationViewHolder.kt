package com.jdm.garam.ui.bus.station.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.databinding.ItemBusStartBinding

class StartStationViewHolder(private val binding: ItemBusStartBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.apply {
            station = item
            executePendingBindings()
        }
    }
}