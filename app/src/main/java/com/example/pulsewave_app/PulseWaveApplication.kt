package com.example.pulsewave_app

import android.app.Application
import android.content.Context


class PulseWaveApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}