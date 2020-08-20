package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionSettingsImpl : SolutionSettingsApi {

    data class State(
        val developerSettings: Boolean = false
    )

    sealed class Action {
        object OpenDeveloperSettings : Action()
    }

    val store = createStore(State()) { s, action: Action ->
        when (action) {
            is Action.OpenDeveloperSettings -> {
                s.copy(developerSettings = !s.developerSettings)
            }
        }
    }

    val update: Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun isDeveloperSettingsAvailable() = store.state.developerSettings
    fun actionOpenDeveloperSettings() = store.send(Action.OpenDeveloperSettings)

}
