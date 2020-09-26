package com.sample

class SolutionSearchResultIdeaImpl(
    val common: SolutionSearchResultImpl,
    val searchStart: SolutionSearchStartApi
) : SolutionSearchResultIdeaApi {

    override fun renderSearchResult(builder: IdeaPanelBuilder) {
        builder.apply {
            title("${searchStart.getSearchQuery()}")
            label("Результат поиска:")
            common.store.state.tickets.forEach { ticket ->
                row {
                    label("${ticket.txt} - ${ticket.price} р.")
                    button("Купить") {
                        common.store.send(SolutionSearchResultImpl.Action.BuyTicket(ticket))
                    }
                }
            }
            button("Назад") {
                common.store.send(SolutionSearchResultImpl.Action.Back)
            }
        }
    }

}
