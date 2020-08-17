import SwiftUI
import Foundation
import app_di
import solution_search_form_api_swift

public struct SolutionSearchFormIosImpl
        : SolutionSearchFormIosApi {

    public var common:Solution_search_form_implSolutionSearchFormImpl

    public init(common: Solution_search_form_implSolutionSearchFormImpl) {
        self.common = common
    }

    public func renderSearchForm() -> some View {
        VStack {
            Text("SolutionSearchFormIosImpl")
            Text("Откуда")
            Text(self.common.getState().searchFrom)
            Text("Куда")
            Text(self.common.getState().searchTo)
            Button(action: {
                self.common.send(action: self.common.getActionSearch())
            }) {
                Text("Начать поиск")
            }.padding()

        }
    }

}
