package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionWeatherImpl() : SolutionWeatherApi {
    data class State(
        val emptyData: String = ""
    )

    sealed class Action {
    }

    val store = createStore(
        State()
    ) { s, a: Action ->
        s
    }

    val update: Flow<*> = store.stateFlow

}
