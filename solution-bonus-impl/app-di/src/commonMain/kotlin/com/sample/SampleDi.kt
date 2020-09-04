package com.sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class SampleDi {

    class SolutionAbStub():SolutionAbApi {
        override fun registerBooleanToggle(key: String, defaultValue: Boolean) = Unit
        override fun getBooleanToggleState(key: String): Boolean = true
    }

    data class GlobalState(val updateCount: Int)

    val solutionAb by lazy { SolutionAbStub() }
    val solutionBonus by lazySolution { SolutionBonusImpl(solutionAb) }
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
