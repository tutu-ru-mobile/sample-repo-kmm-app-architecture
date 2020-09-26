package com.sample

import kotlinx.coroutines.delay

class SolutionBuyTelegramImpl(
    val commonImpl: SolutionBuyImpl,
    val paymentPoll: () -> Unit
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
                    launchAppScope {
                        delay(200)
                        paymentPoll()
                    }
                }),
                listOf(TelegramButton("Отмена") {
                    commonImpl.store.send(SolutionBuyImpl.Action.Back())
                })
            )
        )
    }

}
