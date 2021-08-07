package com.scolley.logging.data.source.local

import android.content.Context
import com.scolley.logging.data.source.LoggingDataSource
import com.scolley.logging.data.Result
import com.scolley.logging.data.User

class LoggingLocalDataSource(val context: Context) : LoggingDataSource {

    override suspend fun login(username: String,
                               password: String): Result<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}