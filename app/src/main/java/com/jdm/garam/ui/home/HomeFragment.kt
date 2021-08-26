package com.jdm.garam.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.databinding.FragmentHomeBinding
import com.jdm.garam.state.BaseState
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModel()
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
        MobileAds.initialize(requireContext()){}
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        binding.adView.adListener = object : AdListener() {
            override fun onAdClosed() {
                val adRequest = AdRequest.Builder().build()
                binding.adView.loadAd(adRequest)

            }
        }
    }
    override fun subscribe() {
        viewModel.coronaStatisticState.observe(viewLifecycleOwner,{
            when(it) {
                is BaseState.Success<*> -> {
                    var statistic = it.SuccessResp as CoronaStatistic
                    binding.homeCoronaPager1.itemPager1CountNumber.text = statistic.sum
                    binding.homeCoronaPager1.itemPager1CuredNumber.text = statistic.cured
                    binding.homeCoronaPager1.itemPager1CurrentNumber.text = statistic.infected
                    binding.homeCoronaPager2.itemPager2CheckingNumber.text = statistic.inspected
                    binding.homeCoronaPager2.itemPager2NegativeJudgeNumber.text = statistic.negative
                    binding.homeCoronaPager3.itemPager3SuspiciousNumber.text = statistic.selfQuarantine
                }
            }
        })
    }

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

}