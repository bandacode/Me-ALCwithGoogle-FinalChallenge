package com.ban2apps.me.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

object AppExecutor {
    fun getInstance(): Executor = Executors.newSingleThreadExecutor()
}