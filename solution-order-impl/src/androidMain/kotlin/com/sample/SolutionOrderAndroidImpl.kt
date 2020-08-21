package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBorder
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.sample.app.SolutionOrderImpl
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
                        Text("Мои заказы:")
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
                Text("Вернуть за ${ticket.getRefundMoneyAmount()}")
            }
        }
    }

}
