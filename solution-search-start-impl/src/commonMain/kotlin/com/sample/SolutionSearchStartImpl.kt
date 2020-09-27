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

    sealed class SideEffect {
        class SearchNetworkRequest(val query: String) : SideEffect()
    }

    val store = createStoreWithSideEffect(
        State(),
        { store, sideEffect: SideEffect ->
            when (sideEffect) {
                is SideEffect.SearchNetworkRequest -> {
                    launchAppScope {
                        //Тут доджен быть запрос в сеть, но для простоты сделал эмитацию сетевой задержки
                        delay(500)
                        //Фальшивый ответ от сервера:
                        store.send(
                            Action.SearchResult(
                                listOf(
                                    Ticket(5000, "Сапсан"),
                                    Ticket(3000, "Невский эксп."),
                                    Ticket(1000, "Плацкарт")
                                )
                            )
                        )
                    }
                }
            }
        }
    ) { state, action: Action ->
        when (action) {
            is Action.StartSearch -> {
                ReducerResult(
                    state.copy(
                        searchQuery = action.query
                    ),
                    listOf(
                        SideEffect.SearchNetworkRequest(action.query)
                    )
                )
            }
            is Action.SearchResult -> {
                solutionSearchResult.navigateSearchResult(action.result)
                ReducerResult(state)
            }
        }
    }

    override fun startSearch(searchQuery: String) {
        store.send(Action.StartSearch(searchQuery))
        solutionNavigation.navigate(
            SolutionSearchStartApi.NavSearchStart(),
            BackStackBehaviour.Once
        )
    }

    override fun getSearchQuery(): String = store.state.searchQuery

    override fun onStateUpdate(): Flow<*> = store.stateFlow

}
