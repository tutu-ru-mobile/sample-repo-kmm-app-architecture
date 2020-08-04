package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionTabsImpl(): SolutionTabsApi {
    sealed class Screen {
        object Main : Screen()
        object Orders : Screen()
        object Settings : Screen()
    }

    data class State(
        val screen: Screen = Screen.Main
    )

    sealed class Action {
        object SelectMain : Action()
        object SelectOrders : Action()
        object SelectSettings : Action()
    }

    val store = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.SelectMain -> {
                s.copy(
                    screen = Screen.Main
                )
            }
            is Action.SelectOrders -> {
                s.copy(
                    screen = Screen.Orders
                )
            }
            is Action.SelectSettings -> {
                s.copy(
                    screen = Screen.Settings
                )
            }
        }
    }

    val update: Flow<*> = store.stateFlow
}