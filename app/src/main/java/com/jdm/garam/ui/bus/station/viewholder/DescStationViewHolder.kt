package com.jdm.garam.ui.bus.station.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.Bus
import com.jdm.garam.data.response.BusType
import com.jdm.garam.databinding.ItemBusDescriptionBinding
import com.jdm.garam.databinding.ItemBusEndBinding
import com.jdm.garam.databinding.ItemBusStartBinding

class DescStationViewHolder(private val binding: ItemBusDescriptionBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BusType) {
        binding.apply {
            busType = item
            executePendingBindings()
        }
    }
}