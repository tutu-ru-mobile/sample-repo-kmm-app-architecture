package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionTabSearchImpl(
    initEvent: NavigationEvent
) : SolutionTabSearchApi, SolutionWithState {

    class BackStackItem(
        val event: NavigationEvent,
        val behaviour: BackStackBehaviour
    )

    data class State(
        val backStack: List<BackStackItem>
    )

    sealed class Action {
        object NavigateBack : SolutionTabSearchImpl.Action()
        object NavigateToRoot : SolutionTabSearchImpl.Action()
        class AddToBackStack(val item: BackStackItem) : Action()
    }

    val store = createStore(
        State(backStack = listOf(BackStackItem(initEvent, BackStackBehaviour.Root)))
    ) { state: State, action: Action ->
        when (action) {
            is Action.AddToBackStack -> {
                state.copy(
                    backStack = when (action.item.behaviour) {
                        is BackStackBehaviour.Root -> {
                            listOf(action.item)
                        }
                        is BackStackBehaviour.Screen, BackStackBehaviour.Once -> {
                            state.backStack.filter { it.behaviour != BackStackBehaviour.Once } + action.item
                        }
                    }
                )
            }
            Action.NavigateBack -> {
                state.copy(
                    backStack = if(state.backStack.size > 1) {
                        state.backStack.dropLast(1)
                    } else {
                        state.backStack
                    }
                )
            }
            Action.NavigateToRoot -> {
                state.copy(
                    backStack = listOf(state.backStack.first())
                )
            }
        }
    }

    override fun navigate(
        event: NavigationEvent,
        behaviour: BackStackBehaviour
    ) {
        store.send(Action.AddToBackStack(BackStackItem(event, behaviour)))
    }

    override fun navigateBack() {
        store.send(Action.NavigateBack)
    }

    override fun navigateToRoot() {
        store.send(Action.NavigateToRoot)
    }

    override fun onStateUpdate(): Flow<*> = store.stateFlow

    fun getState(): State {
        return store.state
    }

    fun getLastEvent(): NavigationEvent = store.state.backStack.last().event

}
