package com.jdm.garam.ui.bus.station.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.bus.BusType
import com.jdm.garam.databinding.ItemBusDescriptionBinding

class DescStationViewHolder(private val binding: ItemBusDescriptionBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BusType) {
        binding.apply {
            busType = item
            executePendingBindings()
        }
    }
}