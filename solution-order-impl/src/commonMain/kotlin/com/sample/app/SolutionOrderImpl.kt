package com.sample.app

import com.sample.*
import kotlinx.coroutines.flow.Flow

class SolutionOrderImpl(
    val solutionAuth: SolutionAuthApi,
    val solutionBonus: SolutionBonusApi
) : SolutionOrderApi {

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     */
    fun getColor() = MyColors.SOLUTION_ORDER

    data class State(
        val tickets: List<Ticket> = List(3) {
            Ticket(1000, "Поездка $it")
        }
    )

    sealed class Action {
        class RefundTicket(val ticket: Ticket) : Action()
        class AddTicket(val ticket: Ticket) : Action()
    }

    val store: Store<State, Action> = createStore(State()) { s, a: Action ->
        when (a) {
            is Action.RefundTicket -> {
                solutionBonus.refundTicket(a.ticket.price)
                s.copy(
                    tickets = s.tickets - a.ticket
                )
            }
            is Action.AddTicket -> {
                s.copy(
                    tickets = s.tickets + a.ticket
                )
            }
        }
    }

    override fun refund(ticket: Ticket) {
        store.send(Action.RefundTicket(ticket))
    }

    override fun addTicket(ticket: Ticket) {
        store.send(Action.AddTicket(ticket))
    }

    val update: Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getState() = store.state //todo
    fun send(action: Action) = store.send(action)
    fun getActionRefundTicket(ticket: Ticket) = Action.RefundTicket(ticket)
    fun getRecentOrder() = store.state.tickets.lastOrNull()

}
