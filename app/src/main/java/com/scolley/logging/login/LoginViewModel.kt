package com.scolley.logging.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolley.logging.R
import com.scolley.logging.data.source.LoggingRepository
import com.scolley.logging.data.LoginApi
import com.scolley.logging.data.Result
import com.scolley.logging.util.Util.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val loggingRepository: LoggingRepository): ViewModel() {

    private val _username = MutableLiveData<String>()

    val username: LiveData<String>
        get() = _username

    private val _password = MutableLiveData<String>()

    val password: LiveData<String>
        get() = _password

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        login("test2@qq.com","test1234qq")
    }

    private fun login(username: String, password: String) {

        coroutineScope.launch {

            when (val result = loggingRepository.login(username, password)) {
                is Result.Success -> {
                    _error.value = null
                    UserManager._user.value = result.data
                    UserManager._isLoggedIn.value = true
                    Log.d("test","user = ${UserManager.user}, login = ${UserManager.loginCheck}")
                }
                is Result.Fail -> {
                    _error.value = result.error
                }
                is Result.Error -> {
                    _error.value = result.exception.toString()
                }
                else ->{
                    _error.value = getString(R.string.you_know_nothing)
                }
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}