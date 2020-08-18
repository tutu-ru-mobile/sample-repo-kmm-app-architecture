import SwiftUI
import Foundation
import app_di
import solution_wallet_api_swift

public struct SolutionWalletIosImpl: SolutionWalletIosApi {

    var common: Solution_wallet_implSolutionWalletImpl

    public init(
            common: Solution_wallet_implSolutionWalletImpl
    ) {
        self.common = common
    }

    public func renderWalletAndRefillButton() -> some View {
        VStack {
            renderWallet()
            Button("Пополнить счёт") {
                self.common.addMoney(amount: 1000)
            }
        }.padding()
                .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 1))
                .padding()
    }

    public func renderWallet() -> some View {
        Text("Ваш счёт: \(self.common.getState().moneyAmount)")
    }

}
