import SwiftUI
import Foundation
import app_di
import solution_search_form_api_swift

public struct SolutionSearchFormIosImpl
        : SolutionSearchFormIosApi {

    public init() {

    }

    public func todoRender() -> some View {
        Text("SolutionSearchFormIosImpl")
    }

}
