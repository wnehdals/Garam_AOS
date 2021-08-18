package com.jdm.garam.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
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
    }
    override fun subscribe() {
        viewModel.coronaStatisticState.observe(viewLifecycleOwner,{
            when(it) {
                is BaseState.Success<*> -> {
                    var statistic = it.SuccessResp as CoronaStatistic
                    Log.e("sub", statistic.infected.toString())
                }
            }
        })
    }

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

}