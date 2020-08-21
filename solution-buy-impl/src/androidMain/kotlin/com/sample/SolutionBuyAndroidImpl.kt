package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Button
import androidx.ui.text.style.TextDecoration
import androidx.ui.unit.dp
import androidx.ui.unit.sp
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
