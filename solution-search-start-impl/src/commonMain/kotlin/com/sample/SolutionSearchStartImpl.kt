package com.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class SolutionSearchStartImpl(
    val solutionSearchResult: SolutionSearchResultApi,
    val solutionNavigation: SolutionNavigationApi
) : SolutionSearchStartApi, SolutionWithState {

    data class State(
        val searchQuery: String = ""//Запрос поиска, например Москва -> Санкт-Петербург
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
                solutionSearchResult.navigateSearchResult(a.result)
                s
            }
        }
    }

    override fun startSearch(searchQuery: String) {
        store.send(Action.StartSearch(searchQuery))
        solutionNavigation.navigate(SolutionSearchStartApi.NavSearchStart(), BackStackBehaviour.Once)

        launchCoroutineDirty {
            //Тут доджен быть запрос в сеть, но для простоты сделал эмитацию сетевой задержки
            delay(500)
            completeSearch()
        }
    }

    override fun getSearchQuery(): String = store.state.searchQuery

    override fun onStateUpdate(): Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий

    fun completeSearch() {
        //Фальшивый ответ от сервера для простоты
        store.send(
            Action.SearchResult(
                listOf(
                    Ticket(5000, "Сапсан"), Ticket(3000, "Невский эксп."), Ticket(1000, "Плацкарт")
                )
            )
        )
    }

}
