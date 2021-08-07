package com.scolley.logging.data.source

import com.scolley.logging.data.Result
import com.scolley.logging.data.User

interface LoggingDataSource {

    suspend fun login(username: String, password: String): Result<User>

}