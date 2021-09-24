package com.jdm.garam

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.google.android.gms.ads.MobileAds
import com.jdm.garam.di.appModule
import com.jdm.garam.di.networkModule
import com.jdm.garam.util.AppOpenManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GaramApplication: Application() {
    val appContext: Context = this
    lateinit var appOpenManager: AppOpenManager
    val isApplicationDebug
        get() = isApplicationDebug(appContext)

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GaramApplication)
            modules(appModule)
            modules(networkModule)
        }
        //앱 오프닝 광고
        MobileAds.initialize(
            this
        ) { }
        appOpenManager = AppOpenManager(this)
    }
    /**
     * 디버그모드인지 확인하는 함수
     */
    private fun isApplicationDebug(context: Context): Boolean {
        var debuggable = false
        val pm: PackageManager = context.packageManager
        try {
            val appinfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = 0 != appinfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
        }

        return debuggable
    }
    companion object {
        lateinit var instance: GaramApplication
            private set
    }
}