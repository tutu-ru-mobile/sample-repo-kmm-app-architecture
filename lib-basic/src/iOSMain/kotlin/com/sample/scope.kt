package com.sample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

actual fun todoScope(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) { block() }
}
