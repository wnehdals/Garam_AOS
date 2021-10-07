package com.jdm.garam.ui.realestate

import android.util.Log
import android.view.View
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.Building
import com.jdm.garam.databinding.ActivityRealEstateMainBinding
import com.jdm.garam.state.BaseState
import org.koin.androidx.viewmodel.ext.android.viewModel
class RealEstateMainActivity : ViewBindingActivity<ActivityRealEstateMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_real_estate_main
    private val realEstateAdapter = RealEstateAdapter()
    private val viewModel: RealEstateViewModel by viewModel()
    override fun subscribe() {
        viewModel.realEstateState.observe(this, {
            when(it) {
                is BaseState.Loading -> showProgressDialog()
                is BaseState.Success<*> -> {
                    hideProgressDialog()
                    binding.realestateNotFoundTextview.visibility = View.GONE
                    binding.realestateRecyclerview.visibility = View.VISIBLE
                    var list = (it.SuccessResp as MutableList<Building>)
                    realEstateAdapter.submitList(list)
                }
                is BaseState.Fail<*> -> {
                    hideProgressDialog()
                    binding.realestateRecyclerview.visibility = View.GONE
                    binding.realestateNotFoundTextview.visibility = View.VISIBLE
                }
            }

        })
    }

    override fun initView() {
        setBaseAppBar(getString(R.string.real_estate))
        setBackKey()
        viewModel.getRealEstateData(getString(R.string.data_api_key), 42230, 202102)
        binding.realestateRecyclerview.adapter = realEstateAdapter
    }
}