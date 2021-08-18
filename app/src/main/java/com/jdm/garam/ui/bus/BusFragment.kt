package com.jdm.garam.ui.bus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.databinding.FragmentBusBinding

class BusFragment : ViewBindingFragment<FragmentBusBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_bus



    override fun subscribe() {
    }

    companion object {
        const val TAG = "BusFragment"
        fun newInstance() = BusFragment()
    }
}