package com.jdm.garam.entity

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.webkit.*
import android.widget.Toast
import com.jdm.garam.ProgressDialog

class MovieWebClient(private val context: Context,private val progressbar: ProgressDialog) : WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return super.shouldOverrideUrlLoading(view, url)
    }

    @TargetApi(Build.VERSION_CODES.N) // API 24이상
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        //view?.loadUrl(request?.url.toString())
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        if(error?.errorCode!! > -1)
            Toast.makeText(context, "오류 : ${error?.description}", Toast.LENGTH_SHORT).show()
        //super.onReceivedError(view, request, error)
    }
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        //super.onPageStarted(view, url, favicon)
        progressbar.show()
    }
    override fun onPageFinished(view: WebView?, url: String?) {
        //super.onPageFinished(view, url)
        progressbar.dismiss()

    }
/*
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        handler?.proceed()
    }

 */
}