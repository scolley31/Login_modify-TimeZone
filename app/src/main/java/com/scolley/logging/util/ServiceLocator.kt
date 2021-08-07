package com.scolley.logging.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.scolley.logging.data.source.DefaultLoggingRepository
import com.scolley.logging.data.source.LoggingDataSource
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.data.source.local.LoggingLocalDataSource
import com.scolley.logging.data.source.remote.LoggingRemoteDataSource

object ServiceLocator {

    @Volatile
    var repository: LoggingRepository? = null
        @VisibleForTesting set

    fun provideRepository(context: Context): LoggingRepository {
        synchronized(this) {
            return repository
                ?: repository
                ?: createLoggingRepository(context)
        }
    }

    private fun createLoggingRepository(context: Context): LoggingRepository {
        return DefaultLoggingRepository(LoggingRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): LoggingDataSource {
        return LoggingLocalDataSource(context)
    }
}