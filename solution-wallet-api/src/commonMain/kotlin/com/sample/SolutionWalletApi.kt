package com.sample

interface SolutionWalletApi {
    val availableMoneyAmount: Int
    fun spendMoney(amount: Int)
    fun addMoney(amount: Int)
}
