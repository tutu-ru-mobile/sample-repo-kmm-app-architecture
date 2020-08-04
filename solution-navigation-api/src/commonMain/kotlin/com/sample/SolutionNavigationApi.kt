package com.sample

interface SolutionNavigationApi {
    fun navigateSearchForm()
    fun navigateStartSearch(query: String)
    fun navigateSearchResult(tickets: List<Ticket>)
}