package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionSettingsImpl() : SolutionSettingsApi {

    data class State(
        val todo:String = "placeholder"
    )

    sealed class Action {

    }

    val store = createStore(State()) { s, a: Action ->
        s
    }

    val update: Flow<*> = store.stateFlow

}