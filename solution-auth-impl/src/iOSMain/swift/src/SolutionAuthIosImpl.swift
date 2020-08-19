import SwiftUI
import Foundation
import app_di
import lib_basic_swift
import solution_auth_api_swift

public struct SolutionAuthIosImpl
        : SolutionAuthIosApi {

    var common: Solution_auth_implSolutionAuthImpl

    public init(common: Solution_auth_implSolutionAuthImpl) {
        self.common = common
    }

    public func renderLoginForm() -> some View {
        VStack {
            if (self.common.isAuthorized()) {
                Text("Вы авторизованы: \(self.common.getState().login)")
                Button("Выйти") {
                    self.common.send(action: self.common.getActionLogOut())
                }
            } else if (self.common.getState().enterLogin) {
                LoginInputTextView(label: "login", value: self.common.getState().login) { loginStr in
                    self.common.send(action: self.common.getActionEditLogin(str: loginStr))
                }
                PasswordInputTextView(label: "password", value: self.common.getState().pass) { passwordStr in
                    self.common.send(action: self.common.getActionEditPassword(str: passwordStr))
                }
                Button("Войти") {
                    self.common.send(action: self.common.getActionSubmitLogin())
                }
            } else {
                Text("Вы не авторизованы")
                Button("авторизоваться") {
                    self.common.send(action: self.common.getActionShowLogin())
                }
            }
        }.colorRect(color: common.color)
    }

    public func renderLoginInfo() -> some View {
        VStack {
            if (common.isAuthorized()) {
                Text("Вы авторизованы: \(common.getState().login)")
            } else {
                Text("Вы не авторизованы")
            }
        }.colorRect(color: common.color)
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
            TextField("почта", text: getBoundValue())
                    .font(Font.system(size: 15, weight: .medium, design: .serif))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
        }
                .padding()
    }
}

struct PasswordInputTextView: View {
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
            SecureField("пароль", text: getBoundValue())
                    .font(Font.system(size: 15, weight: .medium, design: .serif))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
        }
                .padding()
    }
}
