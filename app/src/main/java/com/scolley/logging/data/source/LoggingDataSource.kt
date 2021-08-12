package com.scolley.logging.data.source

import com.scolley.logging.data.*
import com.scolley.logging.login.UserManager
import java.lang.Exception

class LoggingDataSource {

    suspend fun login(username: String,
                      password: String): Result<User> {
        try {
            val result = LoginApi.retrofitService.login(
                    "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD" ,username, password)

            result.error?.let {
                return Result.Fail(it)
            }
            return Result.Success(result)

        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    suspend fun getTrafficNews(): Result<TrafficNews> {
        try {
            val result = TrafficApi.retrofitService.TrafficNews()

            result.error?.let {
                return Result.Fail(it)
            }
            return Result.Success(result)

        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    suspend fun updateUser(timeZone: TimeZone): Result<TimezoneResponse> {
        try {
            val result = LoginApi.retrofitService.updateUser(
                    "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD",
                    UserManager.user.value?.sessionToken!!,
                    "application/json",
                    timeZone,
                    UserManager.user.value?.objectId!!
            )

            result.error?.let {
                return Result.Fail(it)
            }
            return Result.Success(result)

        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

}