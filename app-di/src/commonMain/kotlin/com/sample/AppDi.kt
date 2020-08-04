package com.sample

import com.sample.app.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AppDi {
    data class GlobalState(val updateCount: Int)
    val wallet by lazy { SolutionWalletImpl() }
    val auth by lazy { SolutionAuthImpl() }
    val order by lazy { SolutionOrderImpl(auth, wallet) }
    val settingsScreen by lazy { SolutionSettingsImpl() }
    val attention by lazy { SolutionAttentionImpl() }
    val searchFormSolution by lazy { SolutionSearchFormImpl(mainTabNav) }
    val startSearch by lazy { SolutionSearchStartImpl(mainTabNav) }
    val searchResult by lazy { SolutionSearchResultImpl(mainTabNav, order, wallet) }
    val mainTab by lazy { SolutionTabSearchImpl(startSearch, searchResult) }
    val mainNav by lazy { SolutionTabsImpl() }
    val weather by lazy {SolutionWeatherImpl()}
    val ab by lazy { SolutionAbImpl() }

    val mainTabNav: SolutionNavigationApi by lazy {
        object : SolutionNavigationApi {
            override fun navigateSearchForm() =
                mainTab.navigateSearchForm()

            override fun navigateStartSearch(query: String) =
                mainTab.navigateStartSearch(query)

            override fun navigateSearchResult(tickets: List<Ticket>) =
                mainTab.navigateSearchResult(tickets)
        }
    }

    val globalStateFlow = MutableStateFlow(GlobalState(updateCount = 0))
    fun update() {
        val state = globalStateFlow.value
        globalStateFlow.value = state.copy(
            updateCount = state.updateCount + 1
        )
    }

    fun addUpdate(f: Flow<*>) {
        GlobalScope.launch {
            f.collect { update() }
        }
    }

    init {
        addUpdate(wallet.update)
        addUpdate(auth.update)
        addUpdate(order.update)
        addUpdate(settingsScreen.update)
        addUpdate(mainTab.update)
        addUpdate(mainNav.update)
        addUpdate(searchFormSolution.update)
        addUpdate(startSearch.update)
        addUpdate(searchResult.update)
        addUpdate(weather.update)
        addUpdate(ab.update)
    }

}
