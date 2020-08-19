package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionAbImpl() : SolutionAbApi {

    data class State(
        val booleanToggles: Map<String, Boolean>
    )

    sealed class Action {
        class SwitchBooleanAb(val key: String) : Action()
        class RegisterBooleanAb(val key: String, val defaultValue: Boolean) : Action()
    }

    val store = createStore(
        State(emptyMap())
    ) { state, action: Action ->
        when (action) {
            is Action.SwitchBooleanAb -> {
                state.copy(
                    booleanToggles = state.booleanToggles.mapValues {
                        if (it.key == action.key) {
                            !it.value
                        } else {
                            it.value
                        }
                    }
                )
            }
            is Action.RegisterBooleanAb -> {
                state.copy(
                    booleanToggles = state.booleanToggles + with(action) { key to defaultValue }
                )
            }
        }
    }

    override fun registerBooleanToggle(key: String, defaultValue: Boolean) {
        store.send(Action.RegisterBooleanAb(key, defaultValue))
    }

    override fun getBooleanToggleState(key: String): Boolean {
        return store.state.booleanToggles.any { it.key == key && it.value }
    }

    val update: Flow<*> = store.stateFlow

    //iOS:
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun getActionSwitchWalletAb(key: String) = Action.SwitchBooleanAb(key)

}
