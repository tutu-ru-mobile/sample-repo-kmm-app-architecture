package com.sample

import kotlinx.coroutines.flow.StateFlow

interface StateFlowAndRender<S> {
    val stateFlow: StateFlow<S>
    fun render(state: S): IdeaPanel
}
