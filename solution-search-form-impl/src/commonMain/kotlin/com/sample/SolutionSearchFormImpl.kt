package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionSearchFormImpl(
    val nav: SolutionNavigationApi
): SolutionSearchFormApi {
    data class State(
        val searchFrom: String = "Москва",
        val searchTo: String = "Санкт-Петербург"
    )

    sealed class Action {
        class From(val str: String) : Action()
        class To(val str: String) : Action()
        object Search : Action()
    }

    val store = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.From -> {
                s.copy(
                    searchFrom = a.str
                )
            }
            is Action.To -> {
                s.copy(
                    searchTo = a.str
                )
            }
            is Action.Search -> {
                nav.navigateStartSearch(s.run { "$searchFrom - $searchTo" })
                s
            }

        }
    }

    val update: Flow<*> = store.stateFlow
}
