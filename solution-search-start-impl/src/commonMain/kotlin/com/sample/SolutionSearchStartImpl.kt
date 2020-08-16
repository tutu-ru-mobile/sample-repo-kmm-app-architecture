package com.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class SolutionSearchStartImpl(val nav: SolutionNavigationApi) : SolutionSearchStartApi {
    data class State(
        val searchQuery: String = ""
    )

    sealed class Action {
        class StartSearch(val query: String) : Action()
        class SearchResult(val result: List<Ticket>) : Action()
    }

    val store = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.StartSearch -> {
                s.copy(
                    searchQuery = a.query
                )
            }
            is Action.SearchResult -> {
                nav.navigateSearchResult(a.result)
                s
            }
        }
    }

    override fun startSearch(query: String) {
        store.send(Action.StartSearch(query))

        todoScope {
            //todo SideEffect
            delay(1000)
            completeSearch()
        }
    }

    override fun getSearchQuery(): String = store.state.searchQuery

    fun completeSearch() {
        store.send(
            Action.SearchResult(
                listOf(
                    Ticket(5000, "Сапсан"), Ticket(3000, "Невский эксп."), Ticket(1000, "Плацкарт")
                )
            )
        )
    }

    val update: Flow<*> = store.stateFlow
}
