package com.sample

interface SolutionNavigationApi {
    fun navigate(event: NavigationEvent, behaviour: BackStackBehaviour)
    fun navigateBack()
    fun navigateToRoot()
}

sealed class BackStackBehaviour {
    object Root : BackStackBehaviour()
    object Once : BackStackBehaviour()
    object Screen : BackStackBehaviour()
}
