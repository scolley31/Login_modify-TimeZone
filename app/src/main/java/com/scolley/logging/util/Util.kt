package com.scolley.logging.util

import com.scolley.logging.LoggingApplication

object Util {

    fun getString(resourceId: Int): String {
        return LoggingApplication.instance.getString(resourceId)
    }

}