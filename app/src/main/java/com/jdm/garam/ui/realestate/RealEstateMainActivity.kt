package com.jdm.garam.ui.realestate

import android.util.Log
import android.view.View
import androidx.paging.PagingData
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.Building
import com.jdm.garam.databinding.ActivityRealEstateMainBinding
import com.jdm.garam.state.BaseState
import org.koin.androidx.viewmodel.ext.android.viewModel
class RealEstateMainActivity : ViewBindingActivity<ActivityRealEstateMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_real_estate_main
    private var realEstateAdapter = BuildingPagerAdapter()
    private val viewModel: RealEstateViewModel by viewModel()
    override fun subscribe() {
        viewModel.buildingList.observe(this, {
            realEstateAdapter.submitData(lifecycle, it)
        })
    }

    override fun initView() {
        setBaseAppBar(getString(R.string.real_estate))
        setBackKey()
        binding.realestateRecyclerview.adapter = realEstateAdapter
        viewModel.getAptRentData(getString(R.string.data_api_key))
    }
}