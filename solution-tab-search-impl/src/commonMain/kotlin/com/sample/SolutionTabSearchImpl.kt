package com.sample

import kotlinx.coroutines.flow.Flow

/**
 * Реализация навигации для первой вкладки  [поисковая форма] -> [поиск] -> [результат поиска (выдача)] -> [покупка]
 */
class SolutionTabSearchImpl(
    val solutionStartSearch: SolutionSearchStartApi,
    val solutionSearchResult: SolutionSearchResultApi,
    val solutionBuy: SolutionBuyApi
) : SolutionTabSearchApi {

    sealed class Screen {
        object SearchForm : Screen()
        object SearchStart : Screen()
        object SearchResult : Screen()
        object Buy : Screen()
    }

    data class State(
        val screen: Screen = Screen.SearchForm
    )

    sealed class Action {
        class SetScreen(val screen: Screen) : Action()
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
        solutionStartSearch.startSearch(query)
        store.send(Action.SetScreen(Screen.SearchStart))
    }

    override fun navigateSearchResult(tickets: List<Ticket>) {
        solutionSearchResult.setSearchResult(tickets)
        store.send(Action.SetScreen(Screen.SearchResult))
    }

    override fun navigateBuy(ticket: Ticket) {
        solutionBuy.buy(ticket)
        store.send(Action.SetScreen(Screen.Buy))
    }

    val update: Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun isSearchForm(): Boolean {
        return store.state.screen is Screen.SearchForm
    }

    fun isSearchStart(): Boolean {
        return store.state.screen is Screen.SearchStart
    }

    fun isSearchResult(): Boolean {
        return store.state.screen is Screen.SearchResult
    }

    fun isBuy(): Boolean {
        return store.state.screen is Screen.Buy
    }

    fun getState(): State {
        return store.state
    }

}
