package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionBuyImpl(
    val solutionNavigation: SolutionNavigationApi,
    val solutionOrder: SolutionOrderApi,
    val solutionBonus: SolutionBonusApi
) : SolutionBuyApi, SolutionWithState {

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     */
    fun getColor() = MyColors.SOLUTION_BUY

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
                solutionBonus.spendBonuses(s.ticket)
                solutionOrder.addTicket(s.ticket)
                solutionNavigation.navigateToRoot()
                s
            }
            is Action.Back -> {
                solutionNavigation.navigateBack()
                s
            }
        }
    }

    override fun navigateBuy(ticket: Ticket) {
        store.send(Action.SetTicket(ticket))
        solutionNavigation.navigate(SolutionBuyApi.NavBuyEvent(), BackStackBehaviour.Screen)
    }

    fun getPrice(): Int {
        return store.state.ticket.price - solutionBonus.calcDiscount(store.state.ticket)
    }

    override fun onStateUpdate(): Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getState() = store.state
    fun send(action: Action) = store.send(action)
    fun actionBuy() = store.send(Action.BuyTicket())
    fun actionCancel() = store.send(Action.Back())

}
