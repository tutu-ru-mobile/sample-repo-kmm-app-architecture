package com.sample

class SolutionBuyTelegramImpl(
    val commonImpl: SolutionBuyImpl
) : SolutionBuyTelegramApi {

    override fun renderBuy(): TelegramView {
        val ticket = commonImpl.getState().ticket
        return TelegramView.Message(
            """
                Билет ${ticket.txt}
            """.trimIndent(),
            buttons = listOf(
                listOf(TelegramButton("Купить за ${commonImpl.getPrice()} р.") {
                    commonImpl.store.send(SolutionBuyImpl.Action.BuyTicket())
                }),
                listOf(TelegramButton("Отмена") {
                    commonImpl.store.send(SolutionBuyImpl.Action.Back())
                })
            )
        )
    }

}
