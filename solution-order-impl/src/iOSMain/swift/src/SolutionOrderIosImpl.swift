import SwiftUI
import Foundation
import app_di
import solution_order_api_swift

public struct SolutionOrderIosImpl
        : SolutionOrderIosApi {

    var common:Solution_order_implSolutionOrderImpl

    public init(common:Solution_order_implSolutionOrderImpl) {
        self.common = common
    }

    public func renderAllOrders() -> some View {
        VStack {
            Text("SolutionOrderIosImpl renderAllOrders")
            List(self.common.getState().tickets, id: \.id) { ticket in
                TicketView(item: ticket) {
                    self.common.send(action: self.common.getActionRefundTicket(ticket: ticket))
                }
            }
        }
    }

    public func renderNearestOrder() -> some View {
            if let recentOrder = self.common.getRecentOrder() {
                return VStack {
                    Text("Ближайшая поездка:")
                    Text("\(recentOrder.txt)")
                }
            } else {
                return VStack {
                    Text("")
                    Text("У вас нет билетов")
                }
            }
    }

}

struct TicketView: View {
    var order: Solution_order_apiTicket
    var clickAction: () -> Void

    public init(item: Solution_order_apiTicket, clickAction: @escaping () -> ()) {
        self.order = item
        self.clickAction = clickAction
    }

    public var body: some View {
        HStack {
            Text(order.txt).font(.headline)
            Spacer().frame(width: 16)
            Button(action: { self.clickAction() }) {
                Text("Вернуть за \(order.getRefundMoneyAmount()) р.")
            }
        }
    }
}
