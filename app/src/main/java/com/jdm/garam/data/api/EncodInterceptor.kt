package com.jdm.garam.data.api

import okhttp3.Interceptor
import okhttp3.Response

class EncodInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val modified = response.newBuilder()
            .addHeader("Content-type", "application/json; charset=utf-8")
            .build()
        return modified
    }
}