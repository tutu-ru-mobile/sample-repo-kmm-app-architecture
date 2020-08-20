package com.sample

import kotlinx.coroutines.flow.Flow
import kotlin.math.min

class SolutionBonusImpl(
    val solutionAb: SolutionAbApi
) : SolutionBonusApi {

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     *
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
        class Add(val add: Int) : Action()
        class Spend(val amount: Int) : Action()
        class SwitchBuyToggle() : Action()
    }

    val store = createStore(
        State(1000, false)
    ) { s, a: Action ->
        when (a) {
            is Action.Add -> {
                s.copy(bonusAmount = s.bonusAmount + a.add)
            }
            is Action.Spend -> {
                s.copy(bonusAmount = s.bonusAmount - a.amount)
            }
            is Action.SwitchBuyToggle -> {
                s.copy(
                    buyWithBonus = !s.buyWithBonus
                )
            }
        }
    }

    val update: Flow<*> = store.stateFlow

    override fun spendBonuses(amount: Int) {
        store.send(Action.Spend(amount))
    }

    override fun addBonuses(amount: Int) {
        store.send(Action.Add(amount))
    }

    override fun isAvailable(): Boolean {
        return solutionAb.getBooleanToggleState(BONUSES_AB_KEY)
    }

    override fun calcDiscount(fullPrice: Int): Int =
        if (canBuyWithBonus()) {
            min(fullPrice * 40 / 100, getState().bonusAmount)
        } else {
            0
        }

    override fun canBuyWithBonus(): Boolean {
        return getState().buyWithBonus && isAvailable()
    }

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getBonusRules(): String = "Бонусами можно оплатить не более 40% стоимости билета"
    fun getState() = store.state//todo
    fun actionSwitchBuyToggle() = store.send(Action.SwitchBuyToggle())

}
