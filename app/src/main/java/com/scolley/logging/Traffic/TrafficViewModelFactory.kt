package com.scolley.logging.Traffic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scolley.logging.data.source.LoggingDataSource
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.login.LoginViewModel

class TrafficViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrafficViewModel::class.java)) {
            return TrafficViewModel(
                    loggingRepository = LoggingRepository(
                            dataSource = LoggingDataSource()
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}