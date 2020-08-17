import SwiftUI
import Foundation
import app_di
import solution_weather_api_swift

public struct SolutionWeatherIosImpl : SolutionWeatherIosApi {

    public init(

    ) {

    }

    public func todoRender() -> some View {
        HStack {
            Text("todoRender")
        }
    }

}
