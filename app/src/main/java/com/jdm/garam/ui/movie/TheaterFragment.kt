package com.jdm.garam.ui.movie

import android.content.Context
import android.os.Bundle
import android.util.Xml
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.jdm.garam.ProgressDialog
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingFragment
import com.jdm.garam.databinding.FragmentTheaterBinding
import com.jdm.garam.entity.MovieWebChromeClient
import com.jdm.garam.entity.MovieWebClient
import com.jdm.garam.util.THEATER_INDEX

class TheaterFragment : ViewBindingFragment<FragmentTheaterBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_theater

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.theaterWebview != null) {
                    if(System.currentTimeMillis() - backPressedTime < 2000) {
                        requireActivity().finish()
                    } else {
                        if(binding.theaterWebview.canGoBack()) {
                            binding.theaterWebview.goBack()
                        } else {
                            showBackpressedToastMessage()
                            backPressedTime = System.currentTimeMillis()

                        }
                    }

                } else {

                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }
    override fun subscribe() {
    }

    override fun initView() {
        binding.theaterWebview.apply {
            webChromeClient = MovieWebChromeClient()
            settings.javaScriptEnabled = true
            webViewClient = MovieWebClient(requireContext(),ProgressDialog(requireContext(),getString(R.string.loading)))
            settings.loadWithOverviewMode = true    //메타태그 허용 여부
            settings.useWideViewPort = true         //화면 사이즈 맞추기 허용 여부
            settings.javaScriptCanOpenWindowsAutomatically = false //자바스크립트 새창 띄우기 허용 여
            settings.setSupportMultipleWindows(false)   //새창 띄우기 허용 여부
            settings.setDomStorageEnabled(true);        //로컬저장소 허용 여부
            settings.cacheMode = WebSettings.LOAD_NO_CACHE //브라우저 캐시 허용 여부
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING //컨텐츠 사이즈 맞추
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            settings.setSupportZoom(true)          //화면 줌 허용 여부
            settings.builtInZoomControls = true    //화면 확대 축소 허용 여부
            settings.defaultTextEncodingName = Xml.Encoding.UTF_8.name // 한글이 깨지지 않게 UTF8로 읽어들일수 있게 설정
        }
        binding.theaterWebview.loadUrl(THEATER_INDEX)
    }


    companion object {
        const val TAG = "TheaterFragment"
        fun newInstance() = TheaterFragment()
    }
}