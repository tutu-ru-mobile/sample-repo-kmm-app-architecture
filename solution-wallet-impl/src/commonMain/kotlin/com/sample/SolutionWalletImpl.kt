package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionWalletImpl() : SolutionWalletApi {
    data class State(
        val moneyAmount: Int
    )

    sealed class Action {
        class Add(val add:Int) : Action()
        class Spend(val amount: Int) : Action()
    }

    val store = createStore(
        State(1000)
    ) { s, a: Action ->
        when (a) {
            is Action.Add -> {
                s.copy(moneyAmount = s.moneyAmount + a.add)
            }
            is Action.Spend -> {
                s.copy(moneyAmount = s.moneyAmount - a.amount)
            }
        }
    }

    val update: Flow<*> = store.stateFlow
    override val availableMoneyAmount: Int get() = store.state.moneyAmount

    override fun spendMoney(amount: Int) {
        store.send(Action.Spend(amount))
    }

    override fun addMoney(amount: Int) {
        store.send(Action.Add(amount))
    }

}
