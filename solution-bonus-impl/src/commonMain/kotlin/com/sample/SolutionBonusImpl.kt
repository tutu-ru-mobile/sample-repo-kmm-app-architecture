package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionBonusImpl(
    val solutionAb: SolutionAbApi
) : SolutionBonusApi {

    companion object {
        const val BONUSES_AB_KEY = "bonuses_toggle"
    }

    init {
        solutionAb.registerBooleanToggle(BONUSES_AB_KEY, false)
    }

    data class State(
        val bonusAmount: Int
    )

    sealed class Action {
        class Add(val add: Int) : Action()
        class Spend(val amount: Int) : Action()
    }

    val store = createStore(
        State(1000)
    ) { s, a: Action ->
        when (a) {
            is Action.Add -> {
                s.copy(bonusAmount = s.bonusAmount + a.add)
            }
            is Action.Spend -> {
                s.copy(bonusAmount = s.bonusAmount - a.amount)
            }
        }
    }

    val update: Flow<*> = store.stateFlow

    override val availableMoneyAmount: Int get() = store.state.bonusAmount

    override fun spendBonuses(amount: Int) {
        store.send(Action.Spend(amount))
    }

    override fun addBonuses(amount: Int) {
        store.send(Action.Add(amount))
    }

    override fun isAvailable(): Boolean {
        return solutionAb.getBooleanToggleState(BONUSES_AB_KEY)
    }

    val color get() = MyColors.SOLUTION_BONUS

    //iOS:
    fun getState() = store.state

}
