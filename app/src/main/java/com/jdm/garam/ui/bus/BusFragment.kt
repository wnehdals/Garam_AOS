package com.jdm.garam.ui.bus

import android.content.Context
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.databinding.FragmentBusBinding
import com.jdm.garam.ui.bus.type.BusTypeActivity
import com.jdm.garam.util.*

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
                goToBusTypeActivity(10)
            }
            busType2.setOnClickListener {
                goToBusTypeActivity(20)
            }
            busType3.setOnClickListener {
                goToBusTypeActivity(40)
            }
            busType4.setOnClickListener {
                goToBusTypeActivity(30)
            }

        }
    }
    fun goToBusTypeActivity(type: Int) {
        Intent(requireContext(), BusTypeActivity::class.java).run {
            putExtra(BUS_TYPE_ID, type)
            startActivity(this)
        }
    }

    companion object {
        const val TAG = "BusFragment"
        fun newInstance() = BusFragment()
    }
}