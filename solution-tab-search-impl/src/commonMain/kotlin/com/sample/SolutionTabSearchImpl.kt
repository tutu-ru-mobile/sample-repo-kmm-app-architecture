package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionTabSearchImpl(
    val startSearch: SolutionSearchStartApi,
    val searchResult: SolutionSearchResultApi
): SolutionTabSearchApi {

    sealed class Screen {
        object SearchForm : Screen()
        object StartSearch : Screen()
        object SearchResult : Screen()
    }

    data class State(
        val screen: Screen = Screen.SearchForm
    )

    sealed class Action {
        class SetScreen(val screen: Screen): Action()
    }

    val store = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.SetScreen -> {
                s.copy(
                    screen = a.screen
                )
            }
        }
    }

    override fun navigateSearchForm() {
        store.send(Action.SetScreen(Screen.SearchForm))
    }

    override fun navigateStartSearch(query: String) {
        startSearch.startSearch(query)
        store.send(Action.SetScreen(Screen.StartSearch))
    }

    override fun navigateSearchResult(tickets: List<Ticket>) {
        searchResult.setSearchResult(tickets)
        store.send(Action.SetScreen(Screen.SearchResult))
    }

    val update: Flow<*> = store.stateFlow
}
