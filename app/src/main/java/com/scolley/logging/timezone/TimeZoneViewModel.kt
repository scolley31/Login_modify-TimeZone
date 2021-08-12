package com.scolley.logging.timezone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolley.logging.data.User
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.login.UserManager

class TimeZoneViewModel(private val loggingRepository: LoggingRepository) : ViewModel() {

    val userEmail = MutableLiveData<User>()
    val timeZoneList = MutableLiveData<String>()


    init {
        userEmail.value = UserManager.user.value
        showTimeZoneArray()
    }

    private fun showTimeZoneArray() {

        val list = mutableListOf<String>()

        for (i in -11..12) {
            if (i >= 0) {
                list.add("GMT +$i")
            } else {
                list.add("GMT -$i")
            }
        }
        timeZoneList.value = list.toString()

    }




}