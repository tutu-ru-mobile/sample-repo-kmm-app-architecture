package com.sample

interface SolutionSearchStartApi {
    fun startSearch(searchQuery: String)
    fun getSearchQuery(): String

    fun isNavSearchStartEvent(event: NavigationEvent?): Boolean = event is NavSearchStart
    class NavSearchStart() : NavigationEvent {

    }
}
