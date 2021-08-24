package com.jdm.garam.ui.bus

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.data.response.Bus
import com.jdm.garam.databinding.FragmentBusBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.bus.type.BusTypeActivity
import com.jdm.garam.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusFragment : ViewBindingFragment<FragmentBusBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_bus


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(System.currentTimeMillis() - backPressedTime < 2000){
                    requireActivity().finish()
                } else {
                    showBackpressedToastMessage()
                    backPressedTime = System.currentTimeMillis()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }

    override fun initView() {
        initEvent()

    }

    override fun subscribe() {

    }
    fun setDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }
    fun initEvent() {
        with(binding) {
            busType1.setOnClickListener {
                goToBusTypeActivity(BUS_TYPE_URL_1)
            }
            busType2.setOnClickListener {
                goToBusTypeActivity(BUS_TYPE_URL_2)
            }
            busType3.setOnClickListener {
                goToBusTypeActivity(BUS_TYPE_URL_3)
            }
            busType4.setOnClickListener {
                goToBusTypeActivity(BUS_TYPE_URL_4)
            }
            busType5.setOnClickListener {
                goToBusTypeActivity(BUS_TYPE_URL_5)
            }
            busType6.setOnClickListener {
                goToBusTypeActivity(BUS_TYPE_URL_6)
            }
        }
    }
    fun goToBusTypeActivity(url: String) {
        Intent(requireContext(), BusTypeActivity::class.java).run {
            putExtra(BUS_TYPE_ID, url)
            startActivity(this)
        }
    }

    companion object {
        const val TAG = "BusFragment"
        fun newInstance() = BusFragment()
    }
}