package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Button
import androidx.ui.unit.dp
import androidx.ui.unit.sp

class SolutionBuyAndroidImpl(
    val common: SolutionBuyImpl,
    val solutionBonusAndroid:SolutionBonusApiAndroid
) : SolutionBuyAndroidApi {

    @Composable
    override fun renderBuy() {
        Column {
            Text("Билет ${common.store.state.ticket.txt}", fontSize = 20.sp)
            Spacer(Modifier.preferredHeight(16.dp))
            solutionBonusAndroid.renderBonusToggle()
            Spacer(Modifier.preferredHeight(16.dp))
            Button(onClick = {
                common.store.send(SolutionBuyImpl.Action.BuyTicket())
            }) {
                Text("Купить за ${common.getPrice()} р.")
            }
            Spacer(Modifier.preferredHeight(16.dp))
            Button(onClick = {
                common.store.send(SolutionBuyImpl.Action.Back())
            }, backgroundColor = HexColor(0x55FF0000).toComposeColor()) {
                Text("Отмена")
            }

        }
    }

}
