package com.sample

import kotlinx.coroutines.delay

fun wait(condition: () -> Boolean, lambda: () -> Unit) {
    todoScope {
        while (!condition()) {
            delay(1)
        }
        lambda()
    }
}

