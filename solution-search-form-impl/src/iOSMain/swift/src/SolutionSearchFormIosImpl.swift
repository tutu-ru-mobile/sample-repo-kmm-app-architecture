import SwiftUI
import Foundation
import app_di
import solution_search_form_api_swift

public struct SolutionSearchFormIosImpl
        : SolutionSearchFormIosApi {

    public var common: Solution_search_form_implSolutionSearchFormImpl

    public init(common: Solution_search_form_implSolutionSearchFormImpl) {
        self.common = common
    }

    public func renderSearchForm() -> some View {
        return VStack {
            Text("SolutionSearchFormIosImpl")
            MyInputTextView(label: "Откуда", value: self.common.getState().searchFrom) { s in
                self.common.send(action: self.common.getActionFrom(str: s))
            }
            MyInputTextView(label: "Куда", value: self.common.getState().searchTo) { s in
                self.common.send(action: self.common.getActionTo(str: s))
            }
            Button(action: {
                self.common.send(action: self.common.getActionSearch())
            }) {
                Text("Начать поиск")
            }.padding()

        }
    }

}

struct MyInputTextView: View {
    var label: String
    var onEdit: (String) -> Void
    var value: String

    public init(label: String, value: String, onEdit: @escaping (String) -> ()) {
        self.label = label
        self.onEdit = onEdit
        self.value = value
    }

    func getBoundValue() -> Binding<String> {
        Binding<String>(get: { () -> String in
            self.value
        }, set: { s in
            self.onEdit(s)
        })
    }

    public var body: some View {
        HStack {
            Text(label)
            TextField("Город, населённый пункт", text: getBoundValue())
                    .font(Font.system(size: 15, weight: .medium, design: .serif))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
        }
                .padding()
                .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 1))
    }
}
