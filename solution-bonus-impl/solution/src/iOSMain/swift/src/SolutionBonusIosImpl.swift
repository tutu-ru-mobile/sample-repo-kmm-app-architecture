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
                }.colorRect(color: common.getColor(), available: common.isAvailable())
            }
        }
    }

    public func renderBonusCount() -> some View {
        _renderBonusCount()
                .colorRect(color: common.getColor(), available: common.isAvailable())
    }

    public func renderBonusToggle() -> some View {
        VStack {
            if(self.common.isAvailable()) {
                MyToggleView(
                        label: "Использовать бонусы",
                        value: self.common.canBuyWithBonus()
                ) { checked in
                    self.common.actionSwitchBuyToggle()
                }
                if(self.common.canBuyWithBonus()) {
                    _renderBonusCount()
                    Text(self.common.getBonusRules())
                }
            }
        }.colorRect(color: common.getColor(), available: common.isAvailable())
    }

    func _renderBonusCount() -> some View {
        VStack {
            if (self.common.isAvailable()) {
                Text("У вас \(self.common.getState().bonusAmount) бонусов")
            }
        }
    }

}

