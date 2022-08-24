package com.jdm.garam.ui.phonebook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdm.garam.data.response.phonebook.PhoneBook
import com.jdm.garam.data.response.schedule.Schedule
import com.jdm.garam.databinding.ItemCalendarEventBinding
import com.jdm.garam.databinding.ItemPhoneBookBinding

class PhoneBookAdapter : ListAdapter<PhoneBook, PhoneBookAdapter.ViewHolder>(phoneBookDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPhoneBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phoneBook = getItem(position)
        if (phoneBook != null) {
            holder.bind(phoneBook)
        }
    }

    inner class ViewHolder(val binding: ItemPhoneBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhoneBook) {
            with(binding) {
                phoneBookTitle.text = item.name
                phoneBookAddress.text = item.address
                phoneBookDate.text = " 데이터 기준일자 : ${item.date}"
                phoneBookPhone.text = item.phone

            }
        }
    }

    companion object {
        val phoneBookDiffUtil = object : DiffUtil.ItemCallback<PhoneBook>() {
            override fun areItemsTheSame(oldItem: PhoneBook, newItem: PhoneBook): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhoneBook, newItem: PhoneBook): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}