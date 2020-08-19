import SwiftUI
import Foundation
import app_di
import solution_attention_api_swift
import solution_auth_api_swift
import solution_weather_api_swift
import solution_order_api_swift
import solution_bonus_api_swift
import solution_ab_api_swift

public struct SolutionAttentionIosImpl
        <
        TSolutionWeatherIosApi: SolutionWeatherIosApi,
        TSolutionOrderIosApi: SolutionOrderIosApi,
        TSolutionWalletIosApi: SolutionWalletIosApi,
        TSolutionAuthIosApi: SolutionAuthIosApi
        >
        : SolutionAttentionIosApi {

    var common: Solution_attention_implSolutionAttentionImpl
    var auth: Solution_auth_apiSolutionAuthApi
    var ab: Solution_ab_apiSolutionAbApi
    var weatherIos: TSolutionWeatherIosApi
    var orderIos: TSolutionOrderIosApi
    var walletIos: TSolutionWalletIosApi
    var authIos: TSolutionAuthIosApi

    public init(
            common: Solution_attention_implSolutionAttentionImpl,
            auth: Solution_auth_apiSolutionAuthApi,
            ab: Solution_ab_apiSolutionAbApi,
            weatherIos: TSolutionWeatherIosApi,
            orderIos: TSolutionOrderIosApi,
            walletIos: TSolutionWalletIosApi,
            authIos: TSolutionAuthIosApi
    ) {
        self.common = common
        self.auth = auth
        self.ab = ab
        self.weatherIos = weatherIos
        self.orderIos = orderIos
        self.walletIos = walletIos
        self.authIos = authIos
    }

    public func renderMainScreenAttention() -> some View {
        VStack {
            self.walletIos.renderBonusCount()
            if(self.auth.isAuthorized()) {
                self.authIos.renderLoginInfo()
                self.orderIos.renderNearestOrder()
            } else {
                self.weatherIos.renderWeather()
            }
        }
    }

}
