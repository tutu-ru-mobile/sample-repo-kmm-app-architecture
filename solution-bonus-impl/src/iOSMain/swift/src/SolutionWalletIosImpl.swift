import SwiftUI
import Foundation
import app_di
import solution_bonus_api_swift

public struct SolutionWalletIosImpl: SolutionWalletIosApi {

    var common: Solution_bonus_implSolutionBonusImpl

    public init(
            common: Solution_bonus_implSolutionBonusImpl
    ) {
        self.common = common
    }

    public func renderBonusesAndRefillButton() -> some View {
        VStack {
            if (self.common.isAvailable()) {
                VStack {
                    renderBonusCount()
                    Button("Добавить бонусы") {
                        self.common.addBonuses(amount: 1000)
                    }
                }.padding()
                        .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 1))
                        .padding()
            }
        }
    }

    public func renderBonusCount() -> some View {
        VStack {
            if (self.common.isAvailable()) {
                Text("У вас \(self.common.getState().bonusAmount) бонусов")
            }
        }
    }

}
