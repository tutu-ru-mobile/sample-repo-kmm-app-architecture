package com.sample.app

import com.sample.*
import kotlinx.coroutines.flow.Flow

class SolutionOrderImpl(val auth: SolutionAuthApi, val wallet: SolutionWalletApi) :
    SolutionOrderApi {

    data class State(
        val tickets: List<Ticket> = List(3) {
            Ticket(1000, "Поездка $it")
        }
    )

    sealed class Action {
        object LoadOrders : Action()
        class RefundTicket(val ticket: Ticket) : Action()
        class AddTicket(val ticket: Ticket) : Action()
    }

    val store: Store<State, Action> = createStore(State()) { s, a: Action ->
            when (a) {
                is Action.LoadOrders -> {
                    s
                }
                is Action.RefundTicket -> {
                    wallet.addMoney(a.ticket.price/2)
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

    init {
        wait({ auth.isAuthorized() }) {
            store.send(Action.LoadOrders)
        }
    }

    val update: Flow<*> = store.stateFlow
}
