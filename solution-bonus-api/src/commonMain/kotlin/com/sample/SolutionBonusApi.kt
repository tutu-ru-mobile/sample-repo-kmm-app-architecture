package com.sample

interface SolutionBonusApi {
    fun spendBonuses(amount: Int)
    fun refundTicket(ticket:Ticket)//todo Ticket entity
    fun calcDiscount(fullPrice: Int): Int
    fun canBuyWithBonus():Boolean
}
