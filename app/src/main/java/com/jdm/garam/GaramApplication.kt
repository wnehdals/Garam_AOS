package com.jdm.garam

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.jdm.garam.di.appModule
import com.jdm.garam.util.AppOpenManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GaramApplication: Application() {
    lateinit var appOpenManager: AppOpenManager
    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GaramApplication)
            modules(appModule)
        }
        //앱 오프닝 광고
        MobileAds.initialize(
            this
        ) { }
        appOpenManager = AppOpenManager(this)
    }
    companion object {
        var appContext: Context? = null
            private set
    }
}