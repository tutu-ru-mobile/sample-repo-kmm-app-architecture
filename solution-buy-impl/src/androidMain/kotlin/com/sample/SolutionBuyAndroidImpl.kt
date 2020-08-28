package com.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.compose.WrapColorBox

class SolutionBuyAndroidImpl(
    val commonImpl: SolutionBuyImpl,
    val solutionBonusAndroid: SolutionBonusApiAndroid,
    val solutionBonus: SolutionBonusApi
) : SolutionBuyAndroidApi {

    @Composable
    override fun renderBuy() {

        WrapColorBox(color = commonImpl.getColor()) {
            Column {
                Text("Билет ${commonImpl.store.state.ticket.txt}", fontSize = 20.sp)
                Spacer(Modifier.preferredHeight(16.dp))
                solutionBonusAndroid.renderBonusToggle()
                Spacer(Modifier.preferredHeight(16.dp))
                Button(onClick = {
                    commonImpl.store.send(SolutionBuyImpl.Action.BuyTicket())
                }) {
                    Column {
                        if (solutionBonus.canBuyWithBonus()) {
                            Text(
                                "${commonImpl.getState().ticket.price} р.",
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                        Text("Купить за ${commonImpl.getPrice()} р.")
                    }
                }
                Spacer(Modifier.preferredHeight(16.dp))
                Button(onClick = {
                    commonImpl.store.send(SolutionBuyImpl.Action.Back())
                }, backgroundColor = HexColor(0x55FF0000).toComposeColor()) {
                    Text("Отмена")
                }

            }
        }

    }

}
