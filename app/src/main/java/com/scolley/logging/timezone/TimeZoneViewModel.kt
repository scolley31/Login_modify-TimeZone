package com.scolley.logging.timezone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scolley.logging.data.Result
import com.scolley.logging.data.TimeZone
import com.scolley.logging.data.TimezoneResponse
import com.scolley.logging.data.User
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.login.UserManager
import kotlinx.coroutines.*

class TimeZoneViewModel(private val loggingRepository: LoggingRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()

    val user : MutableLiveData<User>
        get() = _user

    val timeZoneList = MutableLiveData<MutableList<String>>()

    private var viewModelJob = Job()

    private val _updateFailedInfo = MutableLiveData<String?>(null)

    val updateFailedInfo : LiveData<String?>
        get() = _updateFailedInfo

    private val _updateSuccessInfo = MutableLiveData<TimezoneResponse?>(null)

    val updateSuccessInfo : LiveData<TimezoneResponse?>
        get() = _updateSuccessInfo


    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        _user.value = UserManager.user.value
    }

    private fun getPositionToGMTTime(index: Int): Int{
        return index - 11
    }

    fun updateTimezone(position: Int){

        if (getPositionToGMTTime(position) != user.value!!.timezone) {

            coroutineScope.launch {

                val result = loggingRepository.updateUser(TimeZone(getPositionToGMTTime(position)))

                when (result) {
                    is Result.Success -> {
                        updateSuccess(getPositionToGMTTime(position),result.data)

                        Log.d("test", "result = $result")
                        Log.d("test", "UserManager = ${UserManager.user.value}")
                        Log.d("test", "user = ${user.value}")

                    }
                    is Result.Fail -> {
                        updateFailed(result.error!!)
                    }
                }

            }

        }
    }

    private fun updateSuccess(newTimezone: Int, timezoneResponse: TimezoneResponse){
        user.value!!.timezone = newTimezone
        UserManager.user.value!!.timezone = newTimezone
        _updateSuccessInfo.value = timezoneResponse
    }

    private fun updateFailed(e: String){
        _updateFailedInfo.value = e
    }




}