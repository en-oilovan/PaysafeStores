package com.android.paysafe

import android.app.Application
import com.android.paysafe.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PaysafeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PaysafeApp)
            modules(AppModules.appModules)
        }
    }
}