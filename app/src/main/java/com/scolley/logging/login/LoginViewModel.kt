package com.scolley.logging.login

import android.util.Log
import android.util.Patterns
import android.widget.Toast
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

    private val _mailFormat = MutableLiveData<Boolean>()

    val mailFormat: LiveData<Boolean>
        get() = _mailFormat

    val password = MutableLiveData<String>()

    private val _completeStatus = MutableLiveData<Boolean>(false)

    val completeStatue: LiveData<Boolean>
        get() = _completeStatus

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val _navigateToTraffic = MutableLiveData<Boolean>()

    val navigateToTraffic: LiveData<Boolean>
        get() = _navigateToTraffic

    private val _loginTest = MutableLiveData<Boolean>()

    val loginTest: LiveData<Boolean>
        get() = _loginTest

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

//    init {
//        login("test2@qq.com","test1234qq")
//    }

    private fun checkEmail() {
        username.value?.let { mail ->
            _mailFormat.value = mail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()
        }
    }

    private fun checkInfoCompleted() {
        password.value?.let { password ->
            _completeStatus.value = password.isNotEmpty() && mailFormat.value == true
        }
    }

    fun clickEditText(mail: Boolean) {
        if (mail) {
            checkEmail()
        }
        checkInfoCompleted()
    }

    fun loginAttempt() {
        if (completeStatue.value!! && mailFormat.value!!) {
            login()
        } else {
            loginFail()
        }
    }

    fun loginFail() {

        _loginTest.value = true

    }

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

            UserManager.user = _user
            UserManager.isLoggedIn.value = true

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun clearLiveData() {

        _user.value = null
        _loginTest.value = null

    }

}