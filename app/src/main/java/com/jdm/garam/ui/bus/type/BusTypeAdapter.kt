package com.jdm.garam.ui.bus.type

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.bus.Bus
import com.jdm.garam.databinding.ItemBusTypeBinding

class BusTypeAdapter: ListAdapter<Bus, BusTypeAdapter.ViewHolder>(busDifUtil) {
    var onClick: (Bus) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBusTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bus = getItem(position)
        if(bus != null) {
            holder.bind(bus)
            holder.binding.itemBusType.setOnClickListener { onClick(bus) }
        }

    }

    class ViewHolder(val binding: ItemBusTypeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Bus) {
            with(binding) {
                itemBusTypeId.text = item.number
                itemBusTypeDescription.text = item.title
                itemBusTypeStartTime.text = item.startTime
                itemBusTypeEndTime.text = item.endTime
            }
        }
    }
    companion object {
        val busDifUtil = object : DiffUtil.ItemCallback<Bus>() {
            override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}