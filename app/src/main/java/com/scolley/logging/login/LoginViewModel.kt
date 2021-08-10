package com.scolley.logging.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolley.logging.R
import com.scolley.logging.data.LoginApi
import com.scolley.logging.data.Result
import com.scolley.logging.data.User
import com.scolley.logging.data.source.LoggingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val loggingRepository: LoggingRepository): ViewModel() {

    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val _navigateToTraffic = MutableLiveData<Boolean>()

    val navigateToTraffic: LiveData<Boolean>
        get() = _navigateToTraffic

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

//    init {
//        login("test2@qq.com","test1234qq")
//    }

    fun login() {

        coroutineScope.launch {

            val result = loggingRepository.login(username.value!!, password.value!!)
            Log.d("test","result = $result")

            _user.value = when (result) {
                is Result.Success -> {
                    result.data
                }
                is Result.Fail -> {
                    null
                }
                is Result.Error -> {
                    null
                }
                else ->{
                    null
                }
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}