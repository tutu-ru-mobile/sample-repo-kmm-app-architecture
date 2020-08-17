import SwiftUI
import Foundation
import app_di
import solution_search_start_api_swift

public struct SolutionSearchStartIosImpl
        : SolutionSearchStartIosApi {

    public init() {

    }

    public func todoRender() -> some View {
        Text("SolutionSearchStartIosImpl")
    }
}
