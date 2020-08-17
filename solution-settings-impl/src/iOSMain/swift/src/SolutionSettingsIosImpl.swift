import SwiftUI
import Foundation
import app_di
import solution_settings_api_swift

public struct SolutionSettingsIosImpl: SolutionSettingsIosApi {

    public init(

    ) {

    }

    public func todoRender() -> some View {
        HStack {
            Text("todoRender")
        }
    }

}
