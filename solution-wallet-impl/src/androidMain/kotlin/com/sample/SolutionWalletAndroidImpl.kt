package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button

class SolutionWalletAndroidImpl(
    val walletSolution: SolutionWalletImpl
) : SolutionWalletApiAndroid {

    @Composable
    override fun renderWalletAndRefillButton() {
        Column {
            renderWallet()
            renderRefillButton()
        }
    }

    @Composable
    override fun renderWallet() {
        Text("Ваш счёт: ${walletSolution.store.stateFlow.value.moneyAmount} руб.")
    }

    @Composable
    override fun renderRefillButton() {
        Button(onClick = {
            walletSolution.store.send(SolutionWalletImpl.Action.Add(1000))
        }) {
            Text("пополнить счёт")
        }
    }

}

