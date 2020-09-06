package com.sample


interface SolutionSearchResultApi {
    fun navigateSearchResult(tickets: List<Ticket>)

    fun isNavSearchResultEvent(event: NavigationEvent?) = event is NavSearchResultEvent
    class NavSearchResultEvent() : NavigationEvent
}

