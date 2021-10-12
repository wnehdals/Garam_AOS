package com.jdm.garam.ui.realestate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.Building
import com.jdm.garam.databinding.ItemBuildingBinding
import com.jdm.garam.databinding.ItemBuildingTimelineBinding

class BuildingPagerAdapter:  PagingDataAdapter<Building, RecyclerView.ViewHolder>(
    buildingDifUtil) {
    val NORMAL = 0
    val TIMELINE = 1
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            if(holder is NormalViewHolder) {
                holder.bind(item)
            } else {
                (holder as TimeLineViewHolder).bind(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == NORMAL) {
            return NormalViewHolder(
                ItemBuildingBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        } else {
            return TimeLineViewHolder(ItemBuildingTimelineBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if(item?.type == 0) {
            return NORMAL
        } else {
            return TIMELINE
        }
    }
    class TimeLineViewHolder(val binding: ItemBuildingTimelineBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Building) {
            with(binding) {
                itemBuildingTimelineTextview.text = item.type.toString()
            }
        }
    }
    class NormalViewHolder(val binding: ItemBuildingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Building) {
            with(binding) {
                buildingCreateAt.text = item.buildCreateAt.toString().trim()
                buildingDealDate.text = item.getDate().trim()
                buildingDong.text = item.dong.trim()
                monthlyFee.text = item.monthlyRent.trim()
                depositFee.text = item.deposit.trim()
                buildingSize.text = item.areaForExclusiveUse.toString().trim()
                buildingFloor.text = item.floor.toString().trim()
                if(item.apartment.isNullOrEmpty())
                    buildingName.text = "비공개"
                else
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