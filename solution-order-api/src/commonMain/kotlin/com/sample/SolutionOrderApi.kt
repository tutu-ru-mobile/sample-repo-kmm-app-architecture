package com.sample

interface SolutionOrderApi {
    fun refund(ticket: Ticket)
    fun addTicket(ticket: Ticket)
}
