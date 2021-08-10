package com.scolley.logging.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.scolley.logging.data.User

object UserManager {

    var user = MutableLiveData<User>()

    val isLoggedIn = MutableLiveData<Boolean>()


}