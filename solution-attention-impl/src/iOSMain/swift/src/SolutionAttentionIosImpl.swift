import SwiftUI
import Foundation
import app_di
import solution_attention_api_swift
import solution_auth_api_swift
import solution_weather_api_swift
import solution_order_api_swift
import solution_wallet_api_swift
import solution_ab_api_swift

public struct SolutionAttentionIosImpl
        <
        TSolutionWeatherIosApi: SolutionWeatherIosApi,
        TSolutionOrderIosApi: SolutionOrderIosApi,
        TSolutionWalletIosApi: SolutionWalletIosApi
        >
        : SolutionAttentionIosApi {

    var common: Solution_attention_implSolutionAttentionImpl
    var auth: Solution_auth_apiSolutionAuthApi
    var ab: Solution_ab_apiSolutionAbApi
    var weatherIos: TSolutionWeatherIosApi
    var orderIos: TSolutionOrderIosApi
    var walletIos: TSolutionWalletIosApi

    public init(
            common: Solution_attention_implSolutionAttentionImpl,
            auth: Solution_auth_apiSolutionAuthApi,
            ab: Solution_ab_apiSolutionAbApi,
            weatherIos: TSolutionWeatherIosApi,
            orderIos: TSolutionOrderIosApi,
            walletIos: TSolutionWalletIosApi
    ) {
        self.common = common
        self.auth = auth
        self.ab = ab
        self.weatherIos = weatherIos
        self.orderIos = orderIos
        self.walletIos = walletIos
    }

    public func renderMainScreenAttention() -> some View {
        VStack {
            Text("renderMainScreenAttention")
            if (self.ab.isWalletAvailable()) {
                self.walletIos.renderWallet()
            } else {
                if(self.auth.isAuthorized()) {
                    self.orderIos.renderNearestOrder()
                } else {
                    self.weatherIos.renderWeather()
                }
            }
        }
    }

}
