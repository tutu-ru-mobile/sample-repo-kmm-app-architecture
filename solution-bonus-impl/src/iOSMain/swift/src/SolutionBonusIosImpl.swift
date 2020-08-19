import SwiftUI
import Foundation
import app_di
import lib_basic_swift
import solution_bonus_api_swift
import lib_basic_swift

public struct SolutionBonusIosImpl: SolutionBonusIosApi {

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
                    _renderBonusCount()
                    Button("Добавить бонусы") {
                        self.common.addBonuses(amount: 1000)
                    }
                }.padding()
                        .overlay(
                                RoundedRectangle(cornerRadius: 10)
                                        .stroke(common.color.toSwiftUI(), lineWidth: 1)
                        )
                        .padding()
            }
        }
    }

    public func renderBonusCount() -> some View {
        _renderBonusCount()
                .overlay(
                        RoundedRectangle(cornerRadius: 10)
                                .stroke(common.color.toSwiftUI(), lineWidth: 1)
                )
    }

    func _renderBonusCount() -> some View {
        VStack {
            if (self.common.isAvailable()) {
                Text("У вас \(self.common.getState().bonusAmount) бонусов")
            }
        }
    }

}

