package com.sample

import react.RBuilder
import react.dom.br
import react.dom.div
import react.dom.h2

class SolutionOrderBrowserImpl(
    val commonImpl: SolutionOrderImpl,
    val browserAuth: SolutionAuthBrowserApi
) : SolutionOrderBrowserApi {

    override fun renderNearestOrder(react: RBuilder) {
        react.apply {
            val recentOrder = commonImpl.store.state.tickets.lastOrNull()
            div {
                if (recentOrder != null) {
                    +"Ближайшая поездка:"
                    br {}
                    +recentOrder.txt

                } else {
                    +"У вас нет билетов"
                }
            }
        }
    }

    override fun renderAllOrders(react: RBuilder) {
        react.apply {
            div {
                if (commonImpl.solutionAuth.isAuthorized()) {
                    h2 {
                        +"Мои заказы:"
                    }
                    commonImpl.store.state.tickets.forEach { ticket ->
                        cel {
                            +"${ticket.txt}"
                            btn("Вернуть") {
                                commonImpl.refund(ticket)
                            }
                        }
                    }
                } else {
                    +"Для просмотра заказов"
                    br {}
                    "Нужно авторизоваться:"
                    br {}
                    br {}
                    browserAuth.renderLoginForm(this)
                }
            }
        }
    }

}
