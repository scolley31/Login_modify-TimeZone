package com.scolley.logging.data.source

import com.scolley.logging.data.Result
import com.scolley.logging.data.TrafficNews
import com.scolley.logging.data.User

class LoggingRepository(val dataSource: LoggingDataSource) {

    suspend fun login(username: String, password: String): Result<User> {
        val result = dataSource.login(username, password)
        return result
    }

    suspend fun getTrafficNews(): Result<TrafficNews> {
        val result = dataSource.getTrafficNews()
        return result
    }

}