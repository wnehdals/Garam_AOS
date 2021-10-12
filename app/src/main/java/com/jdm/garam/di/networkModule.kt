package com.jdm.garam.di

import com.jdm.garam.GaramApplication
import com.jdm.garam.data.api.Api
import com.jdm.garam.data.api.EncodInterceptor
import com.jdm.garam.data.api.RealEstateApi
import com.jdm.garam.util.*
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory(named(BUS)) {
        OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(EncodInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (GaramApplication.instance.isApplicationDebug) {
                        HttpLoggingInterceptor.Level.BODY
                    } else
                        HttpLoggingInterceptor.Level.HEADERS
                })
        }.build()
    }
    single(named(REAL_ESTATE)) {
        Retrofit.Builder()
            .client(get(named(BUS)))
            .baseUrl(REAL_ESTATE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single(named(BUS)) {
        Retrofit.Builder()
            .client(get(named(BUS)))
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single(named(BUS)) {
        provideApi(get(named(BUS)))
    }
    single(named(REAL_ESTATE)) {
        provideRealEstateApi(get(named(REAL_ESTATE)))
    }
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
fun provideRealEstateApi(retrofit: Retrofit): RealEstateApi = retrofit.create(RealEstateApi::class.java)