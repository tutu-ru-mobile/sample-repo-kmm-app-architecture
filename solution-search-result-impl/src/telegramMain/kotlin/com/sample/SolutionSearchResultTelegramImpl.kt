package com.sample

class SolutionSearchResultTelegramImpl(
    val common: SolutionSearchResultImpl,
    val searchStart: SolutionSearchStartApi
) : SolutionSearchResultTelegramApi {

    override fun renderSearchResult(): TelegramView {
        return TelegramView.Message(
            text = """
                ${searchStart.getSearchQuery()}
                Результат поиска:
            """.trimIndent(),
            buttons =
            common.store.state.tickets.map { ticket ->
                listOf(
                    TelegramButton("Купить ${ticket.txt} за ${ticket.price} р.") {
                        common.store.send(SolutionSearchResultImpl.Action.BuyTicket(ticket))
                    }
                )
            } +
                    listOf(
                        listOf(
                            TelegramButton("Назад") {
                                common.store.send(SolutionSearchResultImpl.Action.Back)
                            }
                        )
                    )
        )
    }

}
