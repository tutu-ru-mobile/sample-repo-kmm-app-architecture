package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionTabSearchImpl(
    val startSearch: SolutionSearchStartApi,
    val searchResult: SolutionSearchResultApi
): SolutionTabSearchApi {

    sealed class Screen {
        object SearchForm : Screen()
        object SearchStart : Screen()
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
        store.send(Action.SetScreen(Screen.SearchStart))
    }

    override fun navigateSearchResult(tickets: List<Ticket>) {
        searchResult.setSearchResult(tickets)
        store.send(Action.SetScreen(Screen.SearchResult))
    }

    val update: Flow<*> = store.stateFlow

    //for iOS:
    fun isSearchForm():Boolean {
        return store.state.screen is Screen.SearchForm
    }

    fun isSearchStart():Boolean {
        return store.state.screen is Screen.SearchStart
    }

    fun isSearchResult():Boolean {
        return store.state.screen is Screen.SearchResult
    }

    fun getState():State {
        return store.state
    }

}
