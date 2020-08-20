package com.sample

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

typealias Reducer<S, A> = (S, A) -> S

interface Store<S, A> {
    fun send(action: A)
    val stateFlow: StateFlow<S>
    val state get() = stateFlow.value
}

/**
 * Самая простая реализация MVI архитектуры для слоя представления.
 * В реальном проекте также нужно добавить обработку SideEffect-ов
 */
fun <S, A> createStore(init: S, reducer: Reducer<S, A>): Store<S, A> {
    val mutableStateFlow = MutableStateFlow(init)

    return object : Store<S, A> {
        override fun send(action: A) {
            mutableStateFlow.value = reducer(mutableStateFlow.value, action)
        }
        override val stateFlow: StateFlow<S> = mutableStateFlow
    }
}
