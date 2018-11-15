package com.mgaebz.cromulence

import android.app.Application
import org.koin.android.ext.android.startKoin

class InsultingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, listOf(koinApplicationModule))
    }
}