package com.sample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun wait(condition: () -> Boolean, lambda: () -> Unit) {
    GlobalScope.launch {
        while (!condition()) {
            delay(1)
        }
        lambda()
    }
}
