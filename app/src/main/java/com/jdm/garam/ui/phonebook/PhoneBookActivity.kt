package com.jdm.garam.ui.phonebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.phonebook.PhoneBook
import com.jdm.garam.databinding.ActivityPhoneBookBinding
import com.jdm.garam.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhoneBookActivity : ViewBindingActivity<ActivityPhoneBookBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_phone_book
    private val viewModel:PhoneBookViewModel by viewModel()
    private val phoneBookAdapter = PhoneBookAdapter()
    override fun subscribe() {
        viewModel.phoneBookState.observe(this) {
            phoneBookAdapter.submitList(it)
        }
    }

    override fun initView() {
        binding.phoneBookRecyclerview.adapter = phoneBookAdapter
        viewModel.getEventList(getString(R.string.data_api_key))
    }
}