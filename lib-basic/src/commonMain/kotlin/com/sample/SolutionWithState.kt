package com.sample

import kotlinx.coroutines.flow.Flow

interface SolutionWithState {
    fun onStateUpdate(): Flow<*>
}
