package com.jdm.garam.ui.bus.station

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.bus.BusType
import com.jdm.garam.databinding.ItemBusDescriptionBinding
import com.jdm.garam.databinding.ItemBusEndBinding
import com.jdm.garam.databinding.ItemBusMiddleBinding
import com.jdm.garam.databinding.ItemBusStartBinding
import com.jdm.garam.ui.bus.station.viewholder.DescStationViewHolder
import com.jdm.garam.ui.bus.station.viewholder.EndStationViewHolder
import com.jdm.garam.ui.bus.station.viewholder.MiddleStationViewHolder
import com.jdm.garam.ui.bus.station.viewholder.StartStationViewHolder
import com.jdm.garam.util.STATION_TYPE_DESC
import com.jdm.garam.util.STATION_TYPE_END
import com.jdm.garam.util.STATION_TYPE_MIDDLE
import com.jdm.garam.util.STATION_TYPE_START

class BusStationAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = mutableListOf<BusType>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            STATION_TYPE_DESC -> DescStationViewHolder(ItemBusDescriptionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            STATION_TYPE_START -> StartStationViewHolder(ItemBusStartBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            STATION_TYPE_MIDDLE -> MiddleStationViewHolder(ItemBusMiddleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            else -> EndStationViewHolder(ItemBusEndBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val busType = list.get(position)
        if(busType != null) {
            when(holder) {
                is DescStationViewHolder -> holder.bind(busType)
                is StartStationViewHolder -> holder.bind(busType.stations)
                is MiddleStationViewHolder -> holder.bind(busType.stations)
                else -> (holder as EndStationViewHolder).bind(busType.stations)
            }
        }
    }
    fun addData(data: MutableList<BusType>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        var item = list.get(position)
        return when(item.type) {
            STATION_TYPE_DESC -> STATION_TYPE_DESC
            STATION_TYPE_START -> STATION_TYPE_START
            STATION_TYPE_MIDDLE -> STATION_TYPE_MIDDLE
            else -> STATION_TYPE_END
        }
    }
}