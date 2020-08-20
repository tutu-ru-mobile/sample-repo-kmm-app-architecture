package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionAbImpl() : SolutionAbApi {

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     */
    fun getColor(): HexColor = MyColors.SOLUTION_AB

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
    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun getActionSwitchBooleanToggle(key: String) = Action.SwitchBooleanAb(key)
    fun getToggles(): List<BooleanToggle> = store.state.booleanToggles.entries
        .map { BooleanToggle(it.key, it.value) }

}

class BooleanToggle(val key: String, val value: Boolean)
