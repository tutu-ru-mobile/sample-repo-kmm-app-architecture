import SwiftUI
import Foundation
import app_di
import solution_auth_api_swift

public struct SolutionAuthIosImpl
        : SolutionAuthIosApi {

    var common: Solution_auth_implSolutionAuthImpl

    public init(common: Solution_auth_implSolutionAuthImpl) {
        self.common = common
    }

    public func renderLoginForm() -> some View {
        VStack {
            Text("renderLoginForm")
            if (self.common.isAuthorized()) {

            } else if (self.common.getState().enterLogin) {
                LoginInputTextView(label: "login", value: self.common.getState().login) { loginStr in
                    self.common.send(action: self.common.getActionEditLogin(str: loginStr))
                }
                LoginInputTextView(label: "password", value: self.common.getState().pass) { passwordStr in
                    self.common.send(action: self.common.getActionEditPassword(str: passwordStr))
                }
            } else {
                Text("Вы не авторизованы")
                Button("авторизоваться") {
                    self.common.send(action: self.common.getActionShowLogin())
                }
            }
        }.overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 1))
                .padding()
    }

}

struct LoginInputTextView: View {
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
    }
}
