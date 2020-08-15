import SwiftUI
import Foundation
import app_di
import solution_tabs_api_swift

public struct SolutionTabsIOSImpl:SolutionTabsIOSApi {

    public init() {

    }

    public func renderBottomNavigation() -> some View {
        HStack{
            Text("render1")
            Text("render1")
        }
    }

    public func renderScaffold() -> some View {
        Text("render2")
    }

}
