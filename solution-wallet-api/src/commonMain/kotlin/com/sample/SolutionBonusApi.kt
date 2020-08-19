package com.sample

interface SolutionBonusApi {
    val availableMoneyAmount: Int
    fun spendBonuses(amount: Int)
    fun addBonuses(amount: Int)
    fun isAvailable():Boolean
}
