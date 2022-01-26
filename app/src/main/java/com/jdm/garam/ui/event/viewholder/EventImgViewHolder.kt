package com.jdm.garam.ui.event.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.jdm.garam.data.response.campaign.Event
import com.jdm.garam.databinding.ItemEventImgBinding

class EventImgViewHolder(private val binding: ItemEventImgBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Event){
        binding.apply {
            Glide.with(binding.itemEventImage.context)
                .load(item.content)
                .transform(CenterCrop())
                .into(binding.itemEventImage)
        }
    }
}