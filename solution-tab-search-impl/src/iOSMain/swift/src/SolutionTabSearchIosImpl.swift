import SwiftUI
import Foundation
import app_di
import solution_tab_search_api_swift

public struct SolutionTabSearchIosImpl: SolutionTabSearchIosApi {

    public init() {

    }

    public func renderMainScreen() -> some View {
        HStack {
            Text("renderMainScreen")
        }
    }

}
