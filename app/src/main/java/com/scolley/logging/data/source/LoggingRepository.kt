package com.scolley.logging.data.source

import com.scolley.logging.data.Result
import com.scolley.logging.data.User

interface LoggingRepository {

    suspend fun login(username: String, password: String): Result<User>

}