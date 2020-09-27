package com.sample

class SolutionBuyConsoleImpl(
    val commonImpl: SolutionBuyImpl,
    val solutionBonus: SolutionBonusApi,
    val solutionBonusConsole: SolutionBonusConsoleApi
) : SolutionBuyConsoleApi {

    override fun renderBuy(builder: ConsolePanelBuilder) {
        builder.apply {
            val ticket = commonImpl.getState().ticket
            row {
                spacer()
                title("Билет ${ticket.txt}")
            }
            spacer()
            solutionBonusConsole.renderBonusToggle(this)
            button("Купить за ${commonImpl.getPrice()} р.") {
                commonImpl.store.send(SolutionBuyImpl.Action.BuyTicket())
            }
            spacer()
            button("Отмена") {
                commonImpl.store.send(SolutionBuyImpl.Action.Back())
            }
        }
    }

}
