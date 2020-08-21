import SwiftUI
import Foundation
import app_di
import lib_basic_swift
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
                    self.common.send(action: self.common.getActionSwitchBooleanToggle(key: toggle.key))
                }
            }
        }.colorRect(color: common.getColor())
    }

}

