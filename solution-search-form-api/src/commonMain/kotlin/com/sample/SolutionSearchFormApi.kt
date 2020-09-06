package com.sample

interface SolutionSearchFormApi {
    fun isSearchFormEvent(event: NavigationEvent?): Boolean = event is NavSearchForm
    class NavSearchForm() : NavigationEvent {

    }
}
