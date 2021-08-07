package com.scolley.logging.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.scolley.logging.data.User

object UserManager {

    val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    val _isLoggedIn = MutableLiveData<Boolean>()

    val loginCheck: LiveData<Boolean>
        get() = _isLoggedIn

}