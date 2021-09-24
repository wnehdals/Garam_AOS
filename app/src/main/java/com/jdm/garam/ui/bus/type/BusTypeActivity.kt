package com.jdm.garam.ui.bus.type

import android.content.Intent
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.bus.Bus
import com.jdm.garam.databinding.ActivityBusTypeBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.bus.station.BusStationActivity
import com.jdm.garam.util.BUS_STATION_ID
import com.jdm.garam.util.BUS_TYPE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusTypeActivity : ViewBindingActivity<ActivityBusTypeBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_bus_type
    private val viewModel: BusTypeViewModel by viewModel()
    private val busTypeAdapter = BusTypeAdapter()
    override fun subscribe() {
        viewModel.busState.observe(this, {
            when(it) {
                is BaseState.Loading -> {
                    showProgressDialog()
                }
                is BaseState.Success<*> -> {
                    hideProgressDialog()
                    busTypeAdapter.submitList(it.SuccessResp as MutableList<Bus>)
                }
                is BaseState.Fail<*> -> {
                    hideProgressDialog()
                    showFailToastMessage()
                }
            }
        })
    }


    override fun initView() {
        setBaseAppBar(getString(R.string.bus))
        setBackKey()
        var busTypeUrl = intent.getIntExtra(BUS_TYPE_ID, 0)
        if(busTypeUrl != 0) {
            viewModel.getBusList(busTypeUrl)
        }
        busTypeAdapter.apply {
            onClick = this@BusTypeActivity::onClickRecyclerViewItem
        }
        binding.busTypeRecyclerview.adapter = busTypeAdapter
    }
    private fun onClickRecyclerViewItem(bus: Bus) {
        goToBusStaionActivity(bus)
    }
    private fun goToBusStaionActivity(bus: Bus) {
        Intent(this, BusStationActivity::class.java).run {
            putExtra(BUS_STATION_ID, bus)
            startActivity(this)
        }
    }
}