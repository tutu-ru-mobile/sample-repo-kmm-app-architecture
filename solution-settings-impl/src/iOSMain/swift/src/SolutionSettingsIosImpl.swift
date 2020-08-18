import SwiftUI
import Foundation
import app_di
import solution_settings_api_swift
import solution_auth_api_swift
import solution_wallet_api_swift
import solution_ab_api_swift

public struct SolutionSettingsIosImpl
        <
        TSolutionAuthIosApi: SolutionAuthIosApi,
        TSolutionWalletIosApi: SolutionWalletIosApi,
        TSolutionAbIosApi: SolutionAbIosApi
        >
        : SolutionSettingsIosApi {

    var common: Solution_settings_apiSolutionSettingsApi
    var authIos: TSolutionAuthIosApi
    var walletIos: TSolutionWalletIosApi
    var abIos: TSolutionAbIosApi

    public init(
            common: Solution_settings_apiSolutionSettingsApi,
            authIos: TSolutionAuthIosApi,
            walletIos: TSolutionWalletIosApi,
            abIos: TSolutionAbIosApi
    ) {
        self.common = common
        self.authIos = authIos
        self.walletIos = walletIos
        self.abIos = abIos
    }

    public func renderSettings() -> some View {
        VStack {
            Text("SolutionSettingsIosImpl renderSettings")
            authIos.renderLoginForm()
            walletIos.todoRender()
            abIos.todoRender()
        }
    }

}
