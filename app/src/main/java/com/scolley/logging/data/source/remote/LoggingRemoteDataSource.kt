package com.scolley.logging.data.source.remote

import com.scolley.logging.data.source.LoggingDataSource
import com.scolley.logging.data.LoginApi
import com.scolley.logging.data.Result
import com.scolley.logging.data.User
import java.lang.Exception

object LoggingRemoteDataSource : LoggingDataSource {

    override suspend fun login(username: String,
                               password: String): Result<User> {

        try {
            val result = LoginApi.retrofitService.login("vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD" ,username, password)

            result.error?.let {
                return Result.Fail(it)
            }
            return Result.Success(result)

        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

}