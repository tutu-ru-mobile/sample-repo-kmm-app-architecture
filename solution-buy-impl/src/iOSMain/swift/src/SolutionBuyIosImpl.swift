import SwiftUI
import Foundation
import app_di
import lib_basic_swift
import solution_buy_api_swift
import solution_bonus_api_swift

public struct SolutionBuyIosImpl
        <
        TSolutionBonusIosApi:SolutionBonusIosApi
        >
        : SolutionBuyIosApi {

    var common: Solution_buy_implSolutionBuyImpl
    var solutionBonusIos: TSolutionBonusIosApi
    var solutionBonus: Solution_bonus_apiSolutionBonusApi

    public init(
            common: Solution_buy_implSolutionBuyImpl,
            solutionBonus: Solution_bonus_apiSolutionBonusApi,
            solutionBonusIos: TSolutionBonusIosApi
    ) {
        self.common = common
        self.solutionBonus = solutionBonus
        self.solutionBonusIos = solutionBonusIos
    }

    public func renderBuy() -> some View {
        VStack {
            Text("Билет \(self.common.getState().ticket.txt)")
            solutionBonusIos.renderBonusToggle().padding()
            Button(action: { self.common.actionBuy() }) {
                VStack {
                    if(solutionBonus.canBuyWithBonus()) {
                        Text("\(common.getState().ticket.price) р.").strikethrough(color: Color.red)
                    }
                    Text("Купить за \(common.getPrice()) р.")
                }
            }.padding()
            Button(action: { self.common.actionCancel() }) {
                Text("Отмена")
            }.padding().foregroundColor(Color.red)
        }
    }

}

struct TicketView: View {
    var ticket: Solution_order_apiTicket
    var clickAction: () -> Void

    public init(ticket: Solution_order_apiTicket, clickAction: @escaping () -> ()) {
        self.ticket = ticket
        self.clickAction = clickAction
    }

    public var body: some View {
        HStack {
            Text(ticket.txt).font(.headline)
            Spacer().frame(width: 16)
            Button(action: { self.clickAction() }) {
                Text("Купить за \(ticket.price) р.")
            }
        }
    }
}
