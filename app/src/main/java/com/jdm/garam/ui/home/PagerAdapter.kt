package com.jdm.garam.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.CoronaStatistic

class PagerAdapter(
    var coronaStatisticList: List<CoronaStatistic>
) : RecyclerView.Adapter<PagerAdapter.ImageViewHolder>() {
    private val VIEW_TYPE_FIRST = 1
    private val VIEW_TYPE_SECOND = 2
    private val VIEW_TYPE_THIRD = 3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ViewholderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindData(coronaStatisticList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val coronaStatistic: CoronaStatistic = coronaStatisticList.get(position)
        return when(coronaStatistic.type) {
            1 -> VIEW_TYPE_FIRST
            2 -> VIEW_TYPE_SECOND
            3 -> VIEW_TYPE_THIRD
            else -> VIEW_TYPE_FIRST
        }

    }

    override fun getItemCount(): Int = uriList.size

    class ImageViewHolder(
        private val binding: ViewholderImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(uri: Uri) = with(binding) {
            imageView.load(uri.toString())
        }

    }

}
