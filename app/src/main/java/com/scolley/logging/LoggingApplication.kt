package com.scolley.logging

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class LoggingApplication: Application() {

    companion object {
        var instance: LoggingApplication by Delegates.notNull()
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext
    }
}