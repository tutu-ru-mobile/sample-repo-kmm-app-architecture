package com.sample

import androidx.compose.Composable

interface SolutionWalletApiAndroid {
    @Composable
    fun renderWalletAndRefillButton()

    @Composable
    fun renderWallet()

    @Composable
    fun renderRefillButton()
}