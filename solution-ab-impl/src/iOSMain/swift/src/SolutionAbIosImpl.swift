import SwiftUI
import Foundation
import app_di
import solution_ab_api_swift

public struct SolutionAbIosImpl
        : SolutionAbIosApi {

    var common: Solution_ab_implSolutionAbImpl

    public init(
            common: Solution_ab_implSolutionAbImpl
    ) {
        self.common = common
    }

    public func renderAbSettings() -> some View {
        VStack {
            Text("A/B features:")

            List(self.common.getToggles(), id: \.key) { toggle in
                MyToggleView(label: toggle.key, value: toggle.value) { checked in
                    self.common.send(action: self.common.getActionSwitchWalletAb(key: toggle.key))
                }
            }

        }.padding().overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.green, lineWidth: 1))
    }

}

struct MyToggleView: View {
    var label: String
    var onChange: (Bool) -> Void
    var value: Bool

    public init(label: String, value: Bool, onChange: @escaping (Bool) -> ()) {
        self.label = label
        self.onChange = onChange
        self.value = value
    }

    func getBoundValue() -> Binding<Bool> {
        Binding<Bool>(get: { () -> Bool in
            self.value
        }, set: { s in
            self.onChange(s)
        })
    }

    public var body: some View {
        Toggle(isOn: getBoundValue()) {
            Text(label)
        }
    }
}
