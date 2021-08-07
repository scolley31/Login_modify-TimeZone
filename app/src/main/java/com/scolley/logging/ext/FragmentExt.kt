package com.scolley.logging.ext

import androidx.fragment.app.Fragment
import com.scolley.logging.LoggingApplication
import com.scolley.logging.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as LoggingApplication).loggingRepository
    return ViewModelFactory(repository)
}