package com.sample

import kotlinx.coroutines.flow.StateFlow

interface ConsoleStateFlowAndRender<S> {
    val stateFlow: StateFlow<S>
    fun render(state: S): ConsolePanel
}
