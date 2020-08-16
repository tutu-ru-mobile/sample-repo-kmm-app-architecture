import SwiftUI
import Foundation
import app_di
import solution_tabs_api_swift

public struct SolutionTabsIOSImpl:SolutionTabsIOSApi {

    var common:Solution_tabs_implSolutionTabsImpl

    public init(common: Solution_tabs_implSolutionTabsImpl) {
        self.common = common
    }

    public func renderBottomNavigation() -> some View {
        HStack{
            Text("render1")
            Text("render1")
        }
    }

    public func renderScaffold() -> some View {
        let state: Solution_tabs_implSolutionTabsImpl.State =
                common.store.state as! Solution_tabs_implSolutionTabsImpl.State
        let screen: Solution_tabs_implSolutionTabsImpl.Screen = state.screen

        return VStack {
            if(screen is Solution_tabs_implSolutionTabsImpl.ScreenMain) {
                Text("Main")
            } else if(screen is Solution_tabs_implSolutionTabsImpl.ScreenOrders) {
                Text("Orders")
            } else if(screen is Solution_tabs_implSolutionTabsImpl.ScreenSettings) {
                Text("Settings")
            }
            HStack {
                Button(action: {
                    self.common.store.send(action: Solution_tabs_implSolutionTabsImpl.ActionSelectMain())
                }) {
                    Text("Main")
                }
                Button(action: {
                    self.common.store.send(action: Solution_tabs_implSolutionTabsImpl.ActionSelectOrders())
                }) {
                    Text("Orders")
                }
                Button(action: {
                    self.common.store.send(action: Solution_tabs_implSolutionTabsImpl.ActionSelectSettings())
                }) {
                    Text("Settings")
                }
            }
        }

    }

}

func todoState(state: Solution_tabs_implSolutionTabsImpl.State) {
    let ut = TypeSolutionTabs()
    let v = ut.actionSelectOrders
}
