package com.sample

class SolutionBuyIdeaImpl(
    val commonImpl: SolutionBuyImpl,
    val solutionBonus: SolutionBonusApi,
    val solutionBonusIdea: SolutionBonusIdeaApi
) : SolutionBuyIdeaApi {

    override fun renderBuy(builder: IdeaPanelBuilder) {
        builder.apply {
            val ticket = commonImpl.getState().ticket
            label("Билет ${ticket.txt}")
            solutionBonusIdea.renderBonusToggle(this)
            button("Купить за ${commonImpl.getPrice()} р.") {
                commonImpl.store.send(SolutionBuyImpl.Action.BuyTicket())
            }
            button("Отмена") {
                commonImpl.store.send(SolutionBuyImpl.Action.Back())
            }
        }
    }

}
