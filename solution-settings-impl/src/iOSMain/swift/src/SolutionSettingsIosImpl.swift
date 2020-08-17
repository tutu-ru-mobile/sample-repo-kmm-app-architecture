import SwiftUI
import Foundation
import app_di
import solution_settings_api_swift

public struct SolutionSettingsIosImpl: SolutionSettingsIosApi {

    public init(

    ) {

    }

    public func renderSettings() -> some View {
        HStack {
            Text("SolutionSettingsIosImpl renderSettings")
        }
    }

}
