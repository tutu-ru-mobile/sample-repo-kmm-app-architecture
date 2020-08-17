import SwiftUI
import Foundation
import app_di
import solution_order_api_swift

public struct SolutionOrderIosImpl
        : SolutionOrderIosApi {

    public init() {

    }

    public func todoRender() -> some View {
        Text("todoRender")
    }

}
