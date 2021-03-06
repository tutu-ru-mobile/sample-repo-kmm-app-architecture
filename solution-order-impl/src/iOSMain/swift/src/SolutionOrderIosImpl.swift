import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
import solution_order_api_swift
import solution_auth_api_swift

public struct SolutionOrderIosImpl
        <
        TSolutionAuthIosApi:SolutionAuthIosApi
        >
        : SolutionOrderIosApi {

    var common:Solution_order_implSolutionOrderImpl
    var authIos:TSolutionAuthIosApi

    public init(
            common: Solution_order_implSolutionOrderImpl,
            authIos: TSolutionAuthIosApi
    ) {
        self.common = common
        self.authIos = authIos
    }

    public func renderAllOrders() -> some View {
        VStack {
            if(common.solutionAuth.isAuthorized()) {
                Text("Мои билеты:")
                List(self.common.getState().tickets, id: \.id) { ticket in
                    TicketView(item: ticket) {
                        self.common.send(action: self.common.getActionRefundTicket(ticket: ticket))
                    }
                }
            } else {
                Text("Для просмотра заказов")
                Text("Нужно авторизоваться:")
                self.authIos.renderLoginForm()
            }
        }.colorRect(color: self.common.getColor())
    }

    public func renderNearestOrder() -> some View {
            if let recentOrder = self.common.getRecentOrder() {
                return VStack {
                    Text("Ближайшая поездка:")
                    Text("\(recentOrder.txt)")
                }.colorRect(color: self.common.getColor())
            } else {
                return VStack {
                    Text("")
                    Text("У вас нет билетов")
                }.colorRect(color: self.common.getColor())
            }
    }

}

struct TicketView: View {
    var order: Entity_ticketTicket
    var clickAction: () -> Void

    public init(item: Entity_ticketTicket, clickAction: @escaping () -> ()) {
        self.order = item
        self.clickAction = clickAction
    }

    public var body: some View {
        HStack {
            Text(order.txt).font(.headline)
            Spacer().frame(width: 16)
            Button(action: { self.clickAction() }) {
                Text("Вернуть")
            }
        }
    }
}
