package com.sample

interface SolutionBonusApi {
    fun spendBonuses(amount: Int)
    fun addBonuses(amount: Int)
    fun isAvailable(): Boolean
    fun calcDiscount(fullPrice: Int): Int
    fun buyWithBonus():Boolean
}
