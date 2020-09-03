package com.sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class AppDi {
    data class GlobalState(val updateCount: Int)
    val solutionBonus by lazySolution { SolutionBonusImpl(solutionAb) }
    val solutionAuth by lazySolution { SolutionAuthImpl() }
    val solutionOrder by lazySolution { SolutionOrderImpl(solutionAuth, solutionBonus) }
    val solutionSettings by lazySolution { SolutionSettingsImpl() }
    val solutionAttention by lazySolution { SolutionAttentionImpl() }
    val solutionSearchForm by lazySolution { SolutionSearchFormImpl(solutionNavigation) }
    val solutionSearchStart by lazySolution { SolutionSearchStartImpl(solutionNavigation) }
    val solutionSearchResult by lazySolution { SolutionSearchResultImpl(solutionNavigation) }
    val solutionTabSearch by lazySolution { SolutionTabSearchImpl(solutionSearchStart, solutionSearchResult, solutionBuy) }
    val solutionTabs by lazySolution { SolutionTabsImpl() }
    val solutionWeather by lazySolution {SolutionWeatherImpl()}
    val solutionAb by lazySolution { SolutionAbImpl() }
    val solutionBuy by lazySolution { SolutionBuyImpl(solutionNavigation, solutionOrder, solutionBonus) }

    val solutionNavigation: SolutionNavigationApi by lazy {
        object : SolutionNavigationApi {
            override fun navigateSearchForm() =
                solutionTabSearch.navigateSearchForm()

            override fun navigateStartSearch(query: String) =
                solutionTabSearch.navigateStartSearch(query)

            override fun navigateSearchResult(tickets: List<Ticket>) =
                solutionTabSearch.navigateSearchResult(tickets)

            override fun navigateBuy(ticket: Ticket) =
                solutionTabSearch.navigateBuy(ticket)

        }
    }

    val globalStateFlow = MutableStateFlow(GlobalState(updateCount = 0))

    private fun update() {
        val state = globalStateFlow.value
        globalStateFlow.value = state.copy(
            updateCount = state.updateCount + 1
        )
    }

    private fun addUpdate(f: Flow<*>) {
        launchCoroutineDirty {
            f.collect { update() }
        }
    }

    private fun <T:SolutionWithState>lazySolution(lambda:()->T): Lazy<T> =
        lazy {
            lambda().also {
                addUpdate(it.onStateUpdate())
            }
        }

    //iOS:
    fun getLastState() = globalStateFlow.value

    fun addListener(listener: (GlobalState) -> Unit) {
        launchCoroutineDirty {
            globalStateFlow.collectLatest {
                listener(it)
            }
        }
    }

}
