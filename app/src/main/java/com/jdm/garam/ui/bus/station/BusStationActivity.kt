package com.jdm.garam.ui.bus.station

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.Bus
import com.jdm.garam.data.response.BusType
import com.jdm.garam.databinding.ActivityBusStationBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.bus.type.BusTypeViewModel
import com.jdm.garam.util.BUS_STATION_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusStationActivity : ViewBindingActivity<ActivityBusStationBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_bus_station
    private val viewModel: BusStationViewModel by viewModel()
    private val busStationAdapter = BusStationAdapter()
    override fun subscribe() {
        viewModel.busTypeState.observe(this, {
            when(it) {
                is BaseState.Loading -> showProgressDialog()
                is BaseState.Success<*> -> {
                    hideProgressDialog()
                    busStationAdapter.addData(it.SuccessResp as MutableList<BusType>)
                }
                is BaseState.Fail<*> -> {
                    hideProgressDialog()
                    showFailToastMessage()
                }
            }
        })
    }

    override fun initView() {
        setBaseAppBar(getString(R.string.station_route))
        setBackKey()
        setAppBarColor("#E0E0E0")
        binding.busStationRecyclerview.adapter = busStationAdapter
        var bus = intent.getParcelableExtra<Bus>(BUS_STATION_ID)
        bus.let { viewModel.getBusTypeList(bus!!) }
    }
}