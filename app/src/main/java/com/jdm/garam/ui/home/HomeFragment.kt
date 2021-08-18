package com.jdm.garam.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.databinding.FragmentHomeBinding


class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun subscribe() {
    }

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()

    }
}