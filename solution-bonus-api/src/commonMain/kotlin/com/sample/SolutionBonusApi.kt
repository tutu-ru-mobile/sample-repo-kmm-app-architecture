package com.sample

interface SolutionBonusApi {
    fun spendBonuses(ticket:Ticket)
    fun refundTicket(ticket:Ticket)
    fun calcDiscount(ticket: Ticket): Int
    fun getBonusesAmount(): Int
    fun canBuyWithBonus():Boolean
}
