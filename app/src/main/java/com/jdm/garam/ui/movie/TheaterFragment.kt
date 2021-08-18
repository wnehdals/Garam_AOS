package com.jdm.garam.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.databinding.FragmentTheaterBinding

class TheaterFragment : ViewBindingFragment<FragmentTheaterBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_theater

    override fun subscribe() {
    }

    companion object {
        const val TAG = "TheaterFragment"
        fun newInstance() = TheaterFragment()
    }
}