package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionAbImpl() : SolutionAbApi {
    data class State(
        val walletFeature: Boolean = false
    )

    sealed class Action {
        object SwitchWalletAb : Action()
    }

    val store = createStore(
        State()
    ) { s, a: Action ->
        when (a) {
            is Action.SwitchWalletAb -> {
                s.copy(
                    walletFeature = !s.walletFeature
                )
            }
        }
    }

    val update: Flow<*> = store.stateFlow

    override fun isWalletAvailable(): Boolean {
        return store.state.walletFeature
    }


}
