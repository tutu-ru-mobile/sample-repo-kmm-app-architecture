package com.sample

class SolutionOrderConsoleImpl(
    val commonImpl: SolutionOrderImpl
) : SolutionOrderConsoleApi {

    override fun renderNearestOrder(builder: ConsolePanelBuilder) {
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

    override fun renderAllOrders(builder: ConsolePanelBuilder) {
        builder.apply {
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
