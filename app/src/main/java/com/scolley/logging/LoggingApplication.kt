package com.scolley.logging

import android.app.Application
import android.content.Context
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.util.ServiceLocator
import kotlin.properties.Delegates

class LoggingApplication : Application() {

    val loggingRepository: LoggingRepository
        get() = ServiceLocator.provideRepository(this)

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