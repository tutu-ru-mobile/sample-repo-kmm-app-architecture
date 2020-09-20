package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionSearchResultImpl(
    val navigationApi: SolutionNavigationApi,
    val solutionBuyApi: SolutionBuyApi
) : SolutionSearchResultApi, SolutionWithState {

    data class State(
        val tickets: List<Ticket> = emptyList()
    )

    sealed class Action {
        class SetResult(val tickets: List<Ticket>) : Action()
        class BuyTicket(val ticket: Ticket) : Action()
        object Back : Action()
    }

    val store: Store<State, Action> = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.SetResult -> {
                s.copy(
                    tickets = a.tickets
                )
            }
            is Action.BuyTicket -> {
                solutionBuyApi.navigateBuy(a.ticket)
                s
            }
            is Action.Back -> {
                navigationApi.navigateBack()
                s
            }
        }
    }

    override fun navigateSearchResult(tickets: List<Ticket>) {
        store.send(Action.SetResult(tickets))
        navigationApi.navigate(
            SolutionSearchResultApi.NavSearchResultEvent(),
            BackStackBehaviour.Screen
        )
    }

    override fun onStateUpdate(): Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun getActionBuyTicket(ticket: Ticket) = Action.BuyTicket(ticket)

}
