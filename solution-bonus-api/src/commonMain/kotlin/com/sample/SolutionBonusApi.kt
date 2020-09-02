package com.sample

interface SolutionBonusApi {
    fun spendBonuses(amount: Int)
    fun refundTicket(ticket:Ticket)
    fun calcDiscount(fullPrice: Int): Int
    fun canBuyWithBonus():Boolean
}
