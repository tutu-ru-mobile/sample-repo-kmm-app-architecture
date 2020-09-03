package com.sample

import kotlinx.coroutines.flow.Flow
import kotlin.math.min

class SolutionBonusImpl(
    private val solutionAb: SolutionAbApi
) : SolutionBonusApi {

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     */
    fun getColor() = MyColors.SOLUTION_BONUS

    companion object {
        const val BONUSES_AB_KEY = "bonuses_toggle"
    }

    init {
        solutionAb.registerBooleanToggle(BONUSES_AB_KEY, true)
    }

    data class State(
        val bonusAmount: Int,
        val buyWithBonus: Boolean
    )

    sealed class Action {
        class RefundTicket(val fullPrice: Int) : Action()
        class Add(val add: Int) : Action()
        class Spend(val amount: Int) : Action()
        class SwitchBuyToggle() : Action()
    }

    val store = createStore(
        State(1000, false)
    ) { state, action: Action ->
        when (action) {
            is Action.Add -> {
                state.copy(bonusAmount = state.bonusAmount + action.add)
            }
            is Action.Spend -> {
                state.copy(bonusAmount = state.bonusAmount - action.amount)
            }
            is Action.SwitchBuyToggle -> {
                state.copy(
                    buyWithBonus = !state.buyWithBonus
                )
            }
            is Action.RefundTicket -> {
                state.copy(
                    bonusAmount = state.bonusAmount + action.fullPrice * 9 / 10
                )
            }
        }
    }

    val update: Flow<*> = store.stateFlow

    fun isAvailable(): Boolean {
        return solutionAb.getBooleanToggleState(BONUSES_AB_KEY)
    }

    override fun spendBonuses(ticket: Ticket) {
        store.send(Action.Spend(calcDiscount(ticket)))
    }

    override fun refundTicket(ticket: Ticket) {//todo test
        if (isAvailable()) {
            store.send(
                Action.RefundTicket(
                    calcDiscount(ticket)
                )
            )
        }
    }

    override fun calcDiscount(ticket: Ticket): Int =
        if (canBuyWithBonus()) {
            min(ticket.price * 40 / 100, getState().bonusAmount)
        } else {
            0
        }

    override fun canBuyWithBonus(): Boolean {
        return getState().buyWithBonus && isAvailable()
    }

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getBonusRules(): String = "Бонусами можно оплатить не более 40% стоимости билета"
    fun getState() = store.state
    fun actionSwitchBuyToggle() = store.send(Action.SwitchBuyToggle())
    fun addBonuses(amount: Int) = store.send(Action.Add(amount))

}
