package com.jdm.garam.ui.realestate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.Building
import com.jdm.garam.data.response.bus.Bus
import com.jdm.garam.databinding.ItemBuildingBinding
import com.jdm.garam.databinding.ItemBusTypeBinding

class RealEstateAdapter: ListAdapter<Building, RealEstateAdapter.ViewHolder>(buildingDifUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val building = getItem(position)
        if(building != null) {
            holder.bind(building)
        }
    }

    class ViewHolder(val binding: ItemBuildingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Building) {
            with(binding) {
                buildingCreateAt.text = item.buildCreateAt.toString().trim()
                buildingDealDate.text = item.getDate().trim()
                buildingDong.text = item.dong.trim()
                monthlyFee.text = item.monthlyRent.trim()
                depositFee.text = item.deposit.trim()
                buildingSize.text = item.areaForExclusiveUse.toString().trim()
                buildingFloor.text = item.floor.toString().trim()
                buildingName.text = item.apartment.trim()
            }
        }
    }
    companion object {
        val buildingDifUtil = object : DiffUtil.ItemCallback<Building>() {
            override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
                return oldItem.apartment == newItem.apartment && oldItem.areaForExclusiveUse == newItem.areaForExclusiveUse && oldItem.getDate() == newItem.getDate()
                        && oldItem.deposit == newItem.deposit && oldItem.floor == newItem.floor && oldItem.dealMonth == newItem.dealMonth
            }

            override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
                return oldItem.apartment == newItem.apartment && oldItem.areaForExclusiveUse == newItem.areaForExclusiveUse && oldItem.getDate() == newItem.getDate()
                        && oldItem.deposit == newItem.deposit && oldItem.floor == newItem.floor && oldItem.dealMonth == newItem.dealMonth
            }
        }
    }

}