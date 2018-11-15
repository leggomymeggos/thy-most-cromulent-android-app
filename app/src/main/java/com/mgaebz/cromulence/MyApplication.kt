package com.mgaebz.cromulence

import android.app.Application
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, listOf(koinApplicationModule))
    }
}