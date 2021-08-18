package com.jdm.garam

import android.app.Application
import android.content.Context
import com.jdm.garam.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GaramApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GaramApplication)
            modules(appModule)
        }
    }
    companion object {
        var appContext: Context? = null
            private set
    }
}