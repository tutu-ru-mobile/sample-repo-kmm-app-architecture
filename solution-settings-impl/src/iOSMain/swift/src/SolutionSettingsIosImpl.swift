import SwiftUI
import Foundation
import app_di
import lib_basic_swift
import solution_settings_api_swift
import solution_auth_api_swift
import solution_bonus_api_swift
import solution_ab_api_swift

public struct SolutionSettingsIosImpl
        <
        TSolutionAuthIosApi: SolutionAuthIosApi,
        TSolutionBonusIosApi: SolutionBonusIosApi,
        TSolutionAbIosApi: SolutionAbIosApi
        >
        : SolutionSettingsIosApi {

    var common: Solution_settings_apiSolutionSettingsApi
    var authIos: TSolutionAuthIosApi
    var bonusIos: TSolutionBonusIosApi
    var abIos: TSolutionAbIosApi

    public init(
            common: Solution_settings_apiSolutionSettingsApi,
            authIos: TSolutionAuthIosApi,
            bonusIos: TSolutionBonusIosApi,
            abIos: TSolutionAbIosApi
    ) {
        self.common = common
        self.authIos = authIos
        self.bonusIos = bonusIos
        self.abIos = abIos
    }

    public func renderSettings() -> some View {
        VStack {
            authIos.renderLoginForm()
            bonusIos.renderBonusesAndRefillButton()
            abIos.renderAbSettings()
        }
    }

}
