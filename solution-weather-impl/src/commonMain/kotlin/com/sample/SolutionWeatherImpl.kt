package com.sample

import kotlinx.coroutines.flow.Flow

class SolutionWeatherImpl() : SolutionWeatherApi {

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     */
    fun getColor() = MyColors.SOLUTION_WEATHER

    //В этом Solution-е State пока не используется
    data class State(
        val emptyState: String = "stub"
    )

    sealed class Action {
    }

    val store = createStore(
        State()
    ) { state, action: Action ->
        state.copy()
    }
    val update: Flow<*> = store.stateFlow

    // Для iOS проще пользоваться не State-ом, а специальной прослойкой из helper-функий
    fun getWeatherString() = "Погода +25, Облачно"

}
