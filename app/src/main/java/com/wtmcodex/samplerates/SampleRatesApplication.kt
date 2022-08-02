package com.wtmcodex.samplerates

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleRatesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}