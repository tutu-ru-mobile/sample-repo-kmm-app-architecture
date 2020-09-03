package com.sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SolutionAttentionImpl() : SolutionAttentionApi, SolutionWithState {

    // В это solution-е не используется State

    /**
     * Цвет обводки для простоты понимая архитектуры и разбиения по Solution-ам.
     */
    fun getColor() = MyColors.SOLUTION_ATTENTION

    override fun onStateUpdate(): Flow<*> = flowOf<Unit>()// заглушка

}
