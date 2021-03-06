import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
import solution_weather_api_swift

public struct SolutionWeatherIosImpl : SolutionWeatherIosApi {

    var common: Solution_weather_implSolutionWeatherImpl

    public init(
        common: Solution_weather_implSolutionWeatherImpl
    ) {
        self.common = common
    }

    public func renderWeather() -> some View {
        HStack {
            Text(self.common.getWeatherString())
        }.colorRect(color: common.getColor())
    }

}
