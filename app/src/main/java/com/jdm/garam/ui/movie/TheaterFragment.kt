package com.jdm.garam.ui.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
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

                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }
    fun setDispatcher(){
        requireActivity().onBackPressedDispatcher.addCallback(this, callBack)
    }
    override fun subscribe() {
    }

    override fun initView() {
        binding.theaterWebview.apply {
            webChromeClient = MovieWebChromeClient()
            settings.javaScriptEnabled = true
            webViewClient = MovieWebClient(requireContext(),ProgressDialog(requireContext(),getString(R.string.loading)))
            settings.loadWithOverviewMode = true    //???????????? ?????? ??????
            settings.useWideViewPort = true         //?????? ????????? ????????? ?????? ??????
            settings.javaScriptCanOpenWindowsAutomatically = false //?????????????????? ?????? ????????? ?????? ???
            settings.setSupportMultipleWindows(false)   //?????? ????????? ?????? ??????
            settings.setDomStorageEnabled(true);        //??????????????? ?????? ??????
            settings.cacheMode = WebSettings.LOAD_NO_CACHE //???????????? ?????? ?????? ??????
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING //????????? ????????? ??????
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            settings.setSupportZoom(true)          //?????? ??? ?????? ??????
            settings.builtInZoomControls = true    //?????? ?????? ?????? ?????? ??????
            settings.defaultTextEncodingName = Xml.Encoding.UTF_8.name // ????????? ????????? ?????? UTF8??? ??????????????? ?????? ??????
        }
        binding.theaterWebview.loadUrl(THEATER_INDEX)
    }


    companion object {
        const val TAG = "TheaterFragment"
        fun newInstance() = TheaterFragment()
    }
}