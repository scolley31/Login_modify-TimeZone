package com.scolley.logging.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scolley.logging.MainViewModel
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val loggingRepository: LoggingRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(loggingRepository)

                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(loggingRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}

