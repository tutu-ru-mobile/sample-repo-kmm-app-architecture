package com.sample

interface SolutionBuyApi {
    fun navigateBuy(ticket: Ticket)

    fun isNavBuyEvent(event: NavigationEvent?) = event is NavBuyEvent
    class NavBuyEvent() : NavigationEvent
}
