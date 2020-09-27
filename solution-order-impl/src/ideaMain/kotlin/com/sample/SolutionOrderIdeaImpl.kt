package com.sample

class SolutionOrderIdeaImpl(
    val commonImpl: SolutionOrderImpl
) : SolutionOrderIdeaApi {

    override fun renderNearestOrder(builder: IdeaPanelBuilder) {
        builder.apply {
            val recentOrder = commonImpl.store.state.tickets.lastOrNull()
            if (recentOrder != null) {
                label(
                    """
                    Ближайшая поездка:
                    ${recentOrder.txt}
                    """.trimIndent()
                )
            } else {
                label("У вас нет билетов")
            }
        }
    }

    override fun renderAllOrders(builder: IdeaPanelBuilder) {
        builder.apply {
            title("Мои билеты")
            commonImpl.getState().tickets.forEach { ticket ->
                row {
                    label(ticket.txt)
                    button("Вернуть") {
                        commonImpl.send(SolutionOrderImpl.Action.RefundTicket(ticket))
                    }
                }
            }
        }
    }

}
