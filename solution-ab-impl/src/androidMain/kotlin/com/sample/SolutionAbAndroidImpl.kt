package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.material.Checkbox

class SolutionAbAndroidImpl(
    val common: SolutionAbImpl
) : SolutionAbAndroidApi {

    @Composable
    override fun renderAbSettings() {
        val state = common.store.state
        Column() {
            Text("A/B features:")
            Row() {
                Checkbox(checked = state.walletFeature,
                    onCheckedChange = {
                        common.store.send(SolutionAbImpl.Action.SwitchWalletAb)
                    }
                )
                Text("Wallet")
            }
        }
    }

}

