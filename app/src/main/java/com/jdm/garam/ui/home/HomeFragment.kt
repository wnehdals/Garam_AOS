package com.jdm.garam.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.databinding.FragmentHomeBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.calendar.GaramCalendarActivity
import com.jdm.garam.ui.phonebook.PhoneBookActivity
import com.jdm.garam.ui.realestate.RealEstateMainActivity
import com.jdm.garam.util.MainTabMenu
import com.jdm.garam.util.MenuChangeEventBus
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModel()
    private val menuChangeEventBus by inject<MenuChangeEventBus>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime < 2000) {
                    requireActivity().finish()
                } else {
                    showBackpressedToastMessage()
                    backPressedTime = System.currentTimeMillis()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }

    fun setDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {
        viewModel.getCoronaStatistic()
        MobileAds.initialize(requireContext()) {}
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        binding.adView.adListener = object : AdListener() {
            override fun onAdClosed() {
                val adRequest = AdRequest.Builder().build()
                binding.adView.loadAd(adRequest)

            }
        }
        initEvent()
    }

    override fun subscribe() {
        viewModel.coronaStatisticState.observe(viewLifecycleOwner) {
            when (it) {
                is BaseState.Success<*> -> {
                    var statistic = it.SuccessResp as CoronaStatistic
                    binding.homeCoronaInfoNewInspected.text = "${statistic.infected}명"
                    binding.homeCoronaInfoSum.text = "${statistic.sum}명"
                    binding.homeCoronaInfoInspected.text = "${statistic.inspected}명"
                    binding.homeCoronaInfoCured.text = "${statistic.selfQuarantine}명"
                }
                else -> {return@observe}
            }
        }

    }

    private fun initEvent() {
        with(binding) {
            homeCalendarConstraintlayout.setOnClickListener {
                Intent(requireContext(), GaramCalendarActivity::class.java).run {
                    startActivity(this)
                }
            }
            homeCalendarConstraintlayout2.setOnClickListener {
                goToRealEstateActivity()
            }
            homeCalendarConstraintlayout3.setOnClickListener {
                lifecycleScope.launch {
                    menuChangeEventBus.changeMenu(MainTabMenu.NOTI)
                }
            }
            homePhoneBookConstraintlayout.setOnClickListener {
                goToPhoneBookActivity()
            }
        }

    }
    private fun goToRealEstateActivity() {
        Intent(requireContext(), RealEstateMainActivity::class.java).run {
            startActivity(this)
        }
    }
    private fun goToPhoneBookActivity() {
        Intent(requireContext(), PhoneBookActivity::class.java).run { startActivity(this) }
    }

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

}