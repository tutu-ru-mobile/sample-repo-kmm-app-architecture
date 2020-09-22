package com.sample

import react.RBuilder
import react.dom.br
import react.dom.h2

class SolutionBuyBrowserImpl(
    val commonImpl: SolutionBuyImpl,
    val solutionBonusBrowser: SolutionBonusBrowserApi,
    val solutionBonus: SolutionBonusApi
) : SolutionBuyBrowserApi {

    override fun renderBuy(react: RBuilder) {
        react.apply {
            h2 {
                +"Билет ${commonImpl.store.state.ticket.txt}"
            }
            solutionBonusBrowser.renderBonusToggle(this)
            br {}
//            if (solutionBonus.canBuyWithBonus()) {
//                Text(
//                    "${commonImpl.getState().ticket.price} р.",
//                    textDecoration = TextDecoration.LineThrough
//                )
//            }
            btn("Купить за ${commonImpl.getPrice()} р.") {
                commonImpl.store.send(SolutionBuyImpl.Action.BuyTicket())
            }
            br {}
            btn("Отмена") {
                commonImpl.store.send(SolutionBuyImpl.Action.Back())
            }
        }
    }

}
