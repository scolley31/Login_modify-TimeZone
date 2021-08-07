package com.scolley.logging.data.source

import com.scolley.logging.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.Result

class DefaultLoggingRepository(private val loggingRemoteDataSource: LoggingDataSource,
                               private val loggingLocalDataSource: LoggingDataSource
) : LoggingRepository {

    override suspend fun login(username: String,
                               password: String): com.scolley.logging.data.Result<User> {
        return loggingRemoteDataSource.login(username, password)
    }

}