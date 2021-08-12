package com.scolley.logging.data.source

import com.scolley.logging.data.*

class LoggingRepository(val dataSource: LoggingDataSource) {

    suspend fun login(username: String, password: String): Result<User> {
        return dataSource.login(username, password)
    }

    suspend fun getTrafficNews(): Result<TrafficNews> {
        return dataSource.getTrafficNews()
    }

    suspend fun updateUser(timeZone: TimeZone): Result<TimezoneResponse> {
        return dataSource.updateUser(timeZone)
    }

}