package com.sample

import kotlinx.css.h2
import react.RBuilder
import react.dom.h2

class SolutionOrderBrowserImpl(
    val commonImpl: SolutionOrderImpl
//    val BrowserAuth: SolutionAuthBrowserApi
) : SolutionOrderBrowserApi {

    override fun renderNearestOrder(react: RBuilder) {
        react.apply {
            h2 {
                +"renderNearestOrder"
            }
        }
//        WrapColorBox(color = commonImpl.getColor()) {
//            val recentOrder = commonImpl.store.state.tickets.lastOrNull()
//            if (recentOrder != null) {
//                Column() {
//                    Text("Ближайшая поездка:")
//                    Text("${recentOrder.txt}")
//                }
//            } else {
//                Text("У вас нет билетов")
//            }
//        }
    }

    override fun renderAllOrders(react: RBuilder) {
//        WrapColorBox(color = commonImpl.getColor()) {
//            Central {
//                Column {
//                    if (commonImpl.solutionAuth.isAuthorized()) {
//                        Text("Мои заказы:")
//                        commonImpl.store.state.tickets.forEach {
//                            _renderOrder(it)
//                        }
//                    } else {
//                        Text("Для просмотра заказов")
//                        Text("Нужно авторизоваться:")
//                        Spacer(Modifier.preferredHeight(16.dp))
//                        BrowserAuth.renderLoginForm()
//                    }
//                }
//            }
//        }
    }

    fun _renderOrder(ticket: Ticket) {
//        Row(
//            modifier = Modifier.padding(8.dp) +
//                    Modifier.fillMaxWidth() +
//                    Modifier.drawBorder(2.dp, color = Color(0xFF_44_44_44)) +
//                    Modifier.padding(8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text("${ticket.txt}", fontSize = 20.sp)
//            Button(onClick = {
//                commonImpl.refund(ticket)
//            }) {
//                Text("Вернуть")
//            }
//        }
    }

}
