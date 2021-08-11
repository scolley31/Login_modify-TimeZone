package com.scolley.logging.timezone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolley.logging.data.User
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.login.UserManager

class TimeZoneViewModel(private val loggingRepository: LoggingRepository): ViewModel() {

    val userEmail = MutableLiveData<User>()


    init {
        userEmail.value = UserManager.user.value
    }


}