package com.sample

import com.sample.app.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class AppDi {
    data class GlobalState(val updateCount: Int)
    val solutionBonus by lazy { SolutionBonusImpl(solutionAb) }
    val solutionAuth by lazy { SolutionAuthImpl() }
    val solutionOrder by lazy { SolutionOrderImpl(solutionAuth, solutionBonus) }
    val solutionSettings by lazy { SolutionSettingsImpl() }
    val solutionAttention by lazy { SolutionAttentionImpl() }
    val solutionSearchForm by lazy { SolutionSearchFormImpl(mainTabNav) }
    val solutionSearchStart by lazy { SolutionSearchStartImpl(mainTabNav) }
    val solutionSearchResult by lazy { SolutionSearchResultImpl(mainTabNav, solutionOrder, solutionBonus) }
    val solutionTabSearch by lazy { SolutionTabSearchImpl(solutionSearchStart, solutionSearchResult) }
    val solutionTabs by lazy { SolutionTabsImpl() }
    val solutionWeather by lazy {SolutionWeatherImpl()}
    val solutionAb by lazy { SolutionAbImpl() }

    val mainTabNav: SolutionNavigationApi by lazy {
        object : SolutionNavigationApi {
            override fun navigateSearchForm() =
                solutionTabSearch.navigateSearchForm()

            override fun navigateStartSearch(query: String) =
                solutionTabSearch.navigateStartSearch(query)

            override fun navigateSearchResult(tickets: List<Ticket>) =
                solutionTabSearch.navigateSearchResult(tickets)
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
        todoScope {
            f.collect { update() }
        }
    }

    init {
        addUpdate(solutionBonus.update)
        addUpdate(solutionAuth.update)
        addUpdate(solutionOrder.update)
        addUpdate(solutionSettings.update)
        addUpdate(solutionTabSearch.update)
        addUpdate(solutionTabs.update)
        addUpdate(solutionSearchForm.update)
        addUpdate(solutionSearchStart.update)
        addUpdate(solutionSearchResult.update)
        addUpdate(solutionWeather.update)
        addUpdate(solutionAb.update)

        if (false) {
            todoScope {
                while (true) {
                    delay(2000)
                    update()
                }
            }
        }

    }

    //iOS:
    fun getLastState() = globalStateFlow.value

    fun addListener(listener: (GlobalState) -> Unit) {
        println("my addListener AppDi")
        todoScope {
            globalStateFlow.collectLatest {
                listener(it)
            }
        }
    }

}
