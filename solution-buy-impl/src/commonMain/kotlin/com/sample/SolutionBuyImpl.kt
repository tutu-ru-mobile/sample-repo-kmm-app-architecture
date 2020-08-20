package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionBuyImpl(
    val solutionNavigation: SolutionNavigationApi,
    val solutionOrder: SolutionOrderApi,
    val solutionBonus: SolutionBonusApi
) : SolutionBuyApi {

    data class State(
        val ticket: Ticket = Ticket(0, "заглушка")
    )

    sealed class Action {
        class SetTicket(val ticket: Ticket) : Action()
        class BuyTicket() : Action()
        class Back() : Action()
    }

    val store: Store<State, Action> = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.SetTicket -> {
                s.copy(
                    ticket = a.ticket
                )
            }
            is Action.BuyTicket -> {
                solutionBonus.spendBonuses(s.ticket.price)
                solutionOrder.addTicket(s.ticket)
                s
            }
            is Action.Back -> {
                solutionNavigation.navigateSearchForm()
                s
            }
        }
    }

    override fun buy(ticket: Ticket) {
        store.send(Action.SetTicket(ticket))
    }

    val update: Flow<*> = store.stateFlow

    //iOS:
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun getActionBuyTicket(ticket: Ticket) = Action.BuyTicket(ticket)
}
