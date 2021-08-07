package com.scolley.logging.ext

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import com.scolley.logging.LoggingApplication
import com.scolley.logging.factory.ViewModelFactory

fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as LoggingApplication).loggingRepository
    return ViewModelFactory(repository)
}
