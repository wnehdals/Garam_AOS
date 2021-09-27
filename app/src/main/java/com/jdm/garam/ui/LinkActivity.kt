package com.jdm.garam.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Xml
import android.webkit.WebSettings
import com.jdm.garam.ProgressDialog
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.databinding.ActivityLinkBinding
import com.jdm.garam.entity.MovieWebChromeClient
import com.jdm.garam.entity.MovieWebClient
import com.jdm.garam.util.LINK_URL
import com.jdm.garam.util.THEATER_INDEX

class LinkActivity : ViewBindingActivity<ActivityLinkBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_link
    private var url: String? = null
    override fun subscribe() {

    }

    override fun initView() {
        url = intent.getStringExtra(LINK_URL)
        initWebView(url)
    }
    private fun initWebView(url: String?) {
        binding.linkWebview.apply {
            webChromeClient = MovieWebChromeClient()
            settings.javaScriptEnabled = true
            webViewClient = MovieWebClient(this@LinkActivity,
                ProgressDialog(this@LinkActivity ,getString(R.string.loading))
            )
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
        url?.let { binding.linkWebview.loadUrl(it) }

    }
}