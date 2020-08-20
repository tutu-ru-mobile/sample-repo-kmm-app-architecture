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

    public init(
            common: Solution_buy_implSolutionBuyImpl,
            solutionBonusIos: TSolutionBonusIosApi
    ) {
        self.common = common
        self.solutionBonusIos = solutionBonusIos
    }

    public func renderBuy() -> some View {
        VStack {
            Text("todo renderBuy()")
//            Text(searchStart.getSearchQuery())
//            List(self.common.getState().tickets, id: \.id) { ticket in
//                TicketView(item: ticket) {
//                    self.common.send(action: self.common.getActionBuyTicket(ticket: ticket))
//                }
//            }
        }
    }

}

struct TicketView: View {
    var ticket: Solution_order_apiTicket
    var clickAction: () -> Void

    public init(item: Solution_order_apiTicket, clickAction: @escaping () -> ()) {
        self.ticket = item
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
