package com.minminsweeper.ui.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*

class Timer(var limit: Int) {
    var seconds by mutableStateOf(0)
    var outOfTime by mutableStateOf(false)

    private var isRunning = false
    private var coroutineScope = CoroutineScope(Dispatchers.Default)
    private val secondInterval = 1000L

    fun start() {
        coroutineScope.launch {
            isRunning = true
            while (isRunning) {
                delay(secondInterval)
                seconds++
                if (seconds >= limit - 1) {
                    outOfTime = true
                    break
                }
            }
        }
    }

    fun pause() {
        isRunning = false
    }

    fun restart() {
        if (seconds > 0) start()
    }

    fun end() {
        isRunning = false
        coroutineScope.cancel()
    }

    fun reset() {
        isRunning = false
        coroutineScope.cancel()
        seconds = 0
        outOfTime = false
        coroutineScope = CoroutineScope(Dispatchers.Default)
    }
}
