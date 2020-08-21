import SwiftUI
import Foundation
import app_di
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
                Text("Мои заказы:")
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
