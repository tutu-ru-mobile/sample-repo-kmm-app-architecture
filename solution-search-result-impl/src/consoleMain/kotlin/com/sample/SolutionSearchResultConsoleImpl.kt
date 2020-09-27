package com.sample

class SolutionSearchResultConsoleImpl(
    val common: SolutionSearchResultImpl,
    val searchStart: SolutionSearchStartApi
) : SolutionSearchResultConsoleApi {

    override fun renderSearchResult(builder: ConsolePanelBuilder) {
        builder.apply {
            row {
                spacer()
                title("${searchStart.getSearchQuery()}")
            }
            spacer()
            label("Результат поиска:")
            common.store.state.tickets.forEach { ticket ->
                row {
                    label("${ticket.txt} - ${ticket.price} р.")
                    button("Купить") {
                        common.store.send(SolutionSearchResultImpl.Action.BuyTicket(ticket))
                    }
                }
            }
            spacer()
            button("Назад") {
                common.store.send(SolutionSearchResultImpl.Action.Back)
            }
        }
    }

}
