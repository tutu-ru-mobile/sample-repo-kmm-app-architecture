package com.sample

import react.RBuilder
import react.dom.br
import react.dom.div
import react.dom.h2

class SolutionSearchResultBrowserImpl(
    val common: SolutionSearchResultImpl,
    val searchStart: SolutionSearchStartApi
) : SolutionSearchResultBrowserApi {

    fun renderTicket(react: RBuilder, ticket: Ticket) {
        react.apply {
            cel {
                +"${ticket.txt} - ${ticket.price} р.  "
                btn("Купить") {
                    common.store.send(SolutionSearchResultImpl.Action.BuyTicket(ticket))
                }
            }
        }
    }

    override fun renderSearchResult(react: RBuilder) {
        react.apply {
            h2 {
                +"${searchStart.getSearchQuery()}"
            }
            h2 {
                +"Результат поиска:"
            }
            common.store.state.tickets.forEach {
                renderTicket(this, it)
            }
            br {}
            btn("Назад") {
                common.store.send(SolutionSearchResultImpl.Action.Back)
            }
        }
    }

}
