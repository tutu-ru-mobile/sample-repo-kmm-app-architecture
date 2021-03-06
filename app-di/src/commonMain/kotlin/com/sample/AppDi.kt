package com.sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

data class GlobalState(val updateCount: Int)

class AppDi() : AppDiAbstract<SolutionAuthImpl>(
    auth = SolutionAuthImpl()
)

open class AppDiAbstract<SolutionAuth>(
    auth: SolutionAuth
) where SolutionAuth : SolutionWithState,
        SolutionAuth : SolutionAuthApi {

    val solutionAuth: SolutionAuth by lazySolution {
        auth
    }
    val solutionBonus by lazySolution {
        SolutionBonusImpl(solutionAb)
    }
    val solutionOrder by lazySolution {
        SolutionOrderImpl(solutionAuth, solutionBonus)
    }
    val solutionSettings by lazySolution {
        SolutionSettingsImpl()
    }
    val solutionAttention by lazySolution {
        SolutionAttentionImpl()
    }
    val solutionSearchForm by lazySolution {
        SolutionSearchFormImpl(solutionSearchStart)
    }
    val solutionSearchStart by lazySolution {
        SolutionSearchStartImpl(
            solutionSearchResult,
            solutionNavigation
        )
    }
    val solutionSearchResult by lazySolution {
        SolutionSearchResultImpl(
            solutionNavigation,
            solutionBuy
        )
    }
    val solutionTabSearch by lazySolution {
        SolutionTabSearchImpl(
            initEvent = SolutionSearchFormApi.NavSearchForm()
        )
    }
    val solutionTabs by lazySolution { SolutionTabsImpl() }
    val solutionWeather by lazySolution { SolutionWeatherImpl() }
    val solutionAb by lazySolution { SolutionAbImpl() }
    val solutionBuy by lazySolution {
        SolutionBuyImpl(
            solutionNavigation,
            solutionOrder,
            solutionBonus
        )
    }

    val solutionNavigation: SolutionNavigationApi =
        object : SolutionNavigationApi {
            override fun navigate(
                event: NavigationEvent,
                behaviour: BackStackBehaviour
            ) {
                solutionTabSearch.navigate(event, behaviour)
            }

            override fun navigateBack() {
                solutionTabSearch.navigateBack()
            }

            override fun navigateToRoot() {
                solutionTabSearch.navigateToRoot()
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
        launchAppScope {
            f.collect { update() }
        }
    }

    private fun <T : SolutionWithState> lazySolution(lambda: () -> T): Lazy<T> =
        lazy {
            lambda().also {
                addUpdate(it.onStateUpdate())
            }
        }

    //iOS:
    fun getLastState() = globalStateFlow.value

    fun addListener(listener: (GlobalState) -> Unit) {
        launchAppScope {
            globalStateFlow.collectLatest {
                listener(it)
            }
        }
    }

}
