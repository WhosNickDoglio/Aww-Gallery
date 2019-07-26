package com.nicholasdoglio.eyebleach.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatchers @Inject constructor() : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val network: CoroutineDispatcher = Dispatchers.IO
    override val background: CoroutineDispatcher = Dispatchers.Default
}

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val network: CoroutineDispatcher
    val background: CoroutineDispatcher
}