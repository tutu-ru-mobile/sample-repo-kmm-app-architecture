package com.sample

class SolutionOrderTelegramImpl(
    val commonImpl: SolutionOrderImpl
) : SolutionOrderTelegramApi {

    override fun renderNearestOrder(): TelegramView {
        val recentOrder = commonImpl.store.state.tickets.lastOrNull()
        if (recentOrder != null) {
            return TelegramView.Message(
                """
                Ближайшая поездка:
                ${recentOrder.txt}
            """.trimIndent()
            )
        } else {
            return TelegramView.Message(
                "У вас нет билетов"
            )
        }
    }

    override fun renderAllOrders(): TelegramView {
        return TelegramView.Message(
            "Мои заказы:\n\n" +
                    commonImpl.getState().tickets
                        .map { it.txt }
                        .joinToString("\n")
        )
    }

}
