package com.solarrage.androidtest.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
}