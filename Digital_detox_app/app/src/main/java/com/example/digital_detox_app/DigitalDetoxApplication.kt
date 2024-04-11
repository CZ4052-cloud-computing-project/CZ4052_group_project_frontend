package com.example.digital_detox_app

import android.app.Application
import com.example.digital_detox_app.data.AppContainer
import com.example.digital_detox_app.data.DefaultAppContainer

class DigitalDetoxApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}

