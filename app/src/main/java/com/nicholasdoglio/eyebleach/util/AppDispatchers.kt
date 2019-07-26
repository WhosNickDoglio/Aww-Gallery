package com.nicholasdoglio.eyebleach.util

import java.util.concurrent.Executors
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher

class AppDispatchers @Inject constructor() : DispatcherProvider {
    override val database: CoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    override val ui: CoroutineDispatcher = Dispatchers.Main
    override val background: CoroutineDispatcher = Dispatchers.Default
}

interface DispatcherProvider {
    val ui: CoroutineDispatcher
    val background: CoroutineDispatcher
    val database: CoroutineDispatcher
}
