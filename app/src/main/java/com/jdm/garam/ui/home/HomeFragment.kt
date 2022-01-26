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
import com.jdm.garam.data.response.coronastep.CoronaStep
import com.jdm.garam.databinding.FragmentHomeBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.LinkActivity
import com.jdm.garam.ui.calendar.GaramCalendarActivity
import com.jdm.garam.ui.event.EventActivity
import com.jdm.garam.ui.realestate.RealEstateMainActivity
import com.jdm.garam.util.*
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
        /*
        lifecycleScope.launch(Dispatchers.IO){
            val url = "https://www.samcheok.go.kr/02179/02696.web"
            val doc = Jsoup.connect(url).timeout(1000 * 10).get()  //타임아웃 10초
            val contentData : Elements = doc.select("div.info1 div div div ul li p span")
            contentData.removeAt(contentData.size - 1)
            for(i in contentData.indices step 2) {
                var intected = contentData[i].childNode(0)

            }
            Log.e("contenntData", contentData.javaClass.name)
        }

         */

    }

    override fun initView() {
        viewModel.getCoronaStatistic()
        viewModel.getCoronaStep()
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
        viewModel.coronaStatisticState.observe(viewLifecycleOwner, {
            when (it) {
                is BaseState.Success<*> -> {
                    var statistic = it.SuccessResp as CoronaStatistic
                    binding.homeCoronaPager1.itemPager1CountNumber.text = statistic.sum
                    binding.homeCoronaPager1.itemPager1CuredNumber.text = statistic.cured
                    binding.homeCoronaPager1.itemPager1CurrentNumber.text = statistic.infected
                    binding.homeCoronaPager2.itemPager2CheckingNumber.text = statistic.inspected
                    binding.homeCoronaPager2.itemPager2NegativeJudgeNumber.text = statistic.negative
                    binding.homeCoronaPager3.itemPager3SuspiciousNumber.text =
                        statistic.selfQuarantine
                }
            }
        })
        viewModel.coronaStepState.observe(viewLifecycleOwner, {
            when(it) {
                is BaseState.Success<*> -> {
                    var coronaStep = it.SuccessResp as CoronaStep
                    binding.homeCoronaStep.text = coronaStep.step
                    binding.homeCoronaStepDuration.text = coronaStep.duration
                }
                is BaseState.Fail<*> -> {
                    var coronaStep = it.FailResp as CoronaStep
                    binding.homeCoronaStep.text = coronaStep.step
                    binding.homeCoronaStepDuration.text = coronaStep.duration
                }
            }
        })
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
            homeCoronaStepDetail.setOnClickListener {
                Intent(requireContext(), EventActivity::class.java)
                    .putExtra(CAMPAIGN, CAMPAIGN_CORONA)
                    .putExtra(CAMPAIGN_TITLE, CAMPAIGN_TITLE_CORONA_NOTI)
                    .run { startActivity(this) }
            }
        }

    }
    private fun goToRealEstateActivity() {
        Intent(requireContext(), RealEstateMainActivity::class.java).run {
            startActivity(this)
        }
    }

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

}