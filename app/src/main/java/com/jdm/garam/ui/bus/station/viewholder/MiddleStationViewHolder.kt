package com.jdm.garam.ui.bus.station.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.Bus
import com.jdm.garam.databinding.ItemBusMiddleBinding
import com.jdm.garam.databinding.ItemBusStartBinding

class MiddleStationViewHolder(private val binding: ItemBusMiddleBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.apply {
            station = item
            executePendingBindings()
        }
    }
}