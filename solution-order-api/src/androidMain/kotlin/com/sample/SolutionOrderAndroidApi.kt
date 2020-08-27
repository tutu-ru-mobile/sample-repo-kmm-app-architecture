package com.sample

import androidx.compose.runtime.Composable

interface SolutionOrderAndroidApi {
    @Composable
    fun renderNearestOrder()
    @Composable
    fun renderAllOrders()
}
