package com.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.drawBorder
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.compose.Central
import com.sample.compose.WrapColorBox

class SolutionOrderAndroidImpl(
    val commonImpl: SolutionOrderImpl,
    val androidAuth: SolutionAuthAndroidApi
) : SolutionOrderAndroidApi {

    @Composable
    override fun renderNearestOrder() {
        WrapColorBox(color = commonImpl.getColor()) {
            val recentOrder = commonImpl.store.state.tickets.lastOrNull()
            if (recentOrder != null) {
                Column() {
                    Text("Ближайшая поездка:")
                    Text("${recentOrder.txt}")
                }
            } else {
                Text("У вас нет билетов")
            }
        }
    }

    @Composable
    override fun renderAllOrders() {
        WrapColorBox(color = commonImpl.getColor()) {
            Central {
                Column {
                    if (commonImpl.solutionAuth.isAuthorized()) {
                        Text("Мои билеты:")
                        commonImpl.store.state.tickets.forEach {
                            _renderOrder(it)
                        }
                    } else {
                        Text("Для просмотра заказов")
                        Text("Нужно авторизоваться:")
                        Spacer(Modifier.preferredHeight(16.dp))
                        androidAuth.renderLoginForm()
                    }
                }
            }
        }
    }

    @Composable
    fun renderOrder(ticket: Ticket) {
        _renderOrder(ticket)
    }

    @Composable
    fun _renderOrder(ticket: Ticket) {
        Row(
            modifier = Modifier.padding(8.dp) +
                    Modifier.fillMaxWidth() +
                    Modifier.drawBorder(2.dp, color = Color(0xFF_44_44_44)) +
                    Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("${ticket.txt}", fontSize = 20.sp)
            Button(onClick = {
                commonImpl.refund(ticket)
            }) {
                Text("Вернуть")
            }
        }
    }

}
