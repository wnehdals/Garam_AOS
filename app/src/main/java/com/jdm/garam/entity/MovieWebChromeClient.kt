package com.jdm.garam.entity

import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView

class MovieWebChromeClient: WebChromeClient() {
    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsConfirm(view, url, message, result)
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }
}