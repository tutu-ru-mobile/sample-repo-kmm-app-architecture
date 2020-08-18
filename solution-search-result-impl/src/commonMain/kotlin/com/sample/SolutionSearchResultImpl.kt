package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionSearchResultImpl(
    navigationApi: SolutionNavigationApi,
    orderApi: SolutionOrderApi,
    val wallet: SolutionWalletApi
) : SolutionSearchResultApi {
    data class State(
        val tickets: List<Ticket> = emptyList(),
        val notEnoughMoney:Boolean = false
    )

    sealed class Action {
        class SetResult(val tickets: List<Ticket>) : Action()
        class BuyTicket(val ticket: Ticket) : Action()
    }

    val store: Store<State, Action> = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.SetResult -> {
                s.copy(
                    tickets = a.tickets
                )
            }
            is Action.BuyTicket -> {
                wallet.spendMoney(a.ticket.price)
                orderApi.addTicket(a.ticket)
                navigationApi.navigateSearchForm()
                s
            }
        }
    }

    override fun setSearchResult(tickets: List<Ticket>) {
        store.send(Action.SetResult(tickets))
    }

    val update: Flow<*> = store.stateFlow

    //iOS:
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun getActionBuyTicket(ticket: Ticket) = Action.BuyTicket(ticket)
}
