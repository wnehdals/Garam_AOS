package com.jdm.garam.ui.noti

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Xml
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.activity.OnBackPressedCallback
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.databinding.FragmentNotiBinding
import com.jdm.garam.ui.movie.TheaterFragment
import com.jdm.garam.util.NOTI_URL

class NotiFragment : ViewBindingFragment<FragmentNotiBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_noti

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = object : OnBackPressedCallback(true){
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
        binding.notiWebview.apply {
            settings.javaScriptEnabled = true
            settings.defaultTextEncodingName = Xml.Encoding.UTF_8.name
            settings.useWideViewPort = true         //화면 사이즈 맞추기 허용 여부
            settings.setSupportZoom(true)          //화면 줌 허용 여부
            settings.builtInZoomControls = true    //화면 확대 축소 허용 여부
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING //컨텐츠 사이즈 맞추
        }
        binding.notiWebview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfGVaAe6VUrrPmW4d6pSBtx2rM35Aq1fAHUew3WG73G8qoKBA/viewform?usp=sf_link")
        binding.notiSwipeLayout.setOnRefreshListener {
            binding.notiWebview.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfGVaAe6VUrrPmW4d6pSBtx2rM35Aq1fAHUew3WG73G8qoKBA/viewform?usp=sf_link")
            binding.notiSwipeLayout.isRefreshing = false
        }

    }
    override fun subscribe() {
    }
    fun setDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }

    override fun onPause() {
        Log.e("onpuae","onpause")
        super.onPause()

    }
    companion object {
        const val TAG = "NotiFragment"
        fun newInstance() = NotiFragment()
    }
}