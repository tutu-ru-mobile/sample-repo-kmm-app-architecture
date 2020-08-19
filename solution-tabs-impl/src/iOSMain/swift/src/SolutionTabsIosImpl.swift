import SwiftUI
import Foundation
import app_di
import lib_basic_swift
import solution_tabs_api_swift
import solution_tab_search_api_swift
import solution_order_api_swift
import solution_settings_api_swift

public struct SolutionTabsIosImpl
        <
        TSolutionTabSearchIosApi: SolutionTabSearchIosApi,
        TSolutionOrderIosApi: SolutionOrderIosApi,
        TSolutionSettingsIosApi: SolutionSettingsIosApi
        >
        : SolutionTabsIOSApi {

    var common: Solution_tabs_implSolutionTabsImpl
    var searchTabIos: TSolutionTabSearchIosApi
    var orderIos: TSolutionOrderIosApi
    var settingsIos: TSolutionSettingsIosApi

    public init(
            common: Solution_tabs_implSolutionTabsImpl,
            tabSearchIos: TSolutionTabSearchIosApi,
            orderIos: TSolutionOrderIosApi,
            settingsIos: TSolutionSettingsIosApi
    ) {
        self.common = common
        self.searchTabIos = tabSearchIos
        self.orderIos = orderIos
        self.settingsIos = settingsIos
    }

    public func renderBottomNavigation() -> some View {
        HStack() {
            Button(action: {
                self.common.store.send(action: Solution_tabs_implSolutionTabsImpl.ActionSelectMain())
            }) {
                Text("Main")
            }.padding()
            Button(action: {
                self.common.store.send(action: Solution_tabs_implSolutionTabsImpl.ActionSelectOrders())
            }) {
                Text("Orders")
            }.padding()
            Button(action: {
                self.common.store.send(action: Solution_tabs_implSolutionTabsImpl.ActionSelectSettings())
            }) {
                Text("Settings")
            }.padding()
        }
    }

    public func renderScaffold() -> some View {
        let state = common.store.state as! Solution_tabs_implSolutionTabsImpl.State
        let screen = state.screen

        return VStack(alignment: .center) {
            if (screen is Solution_tabs_implSolutionTabsImpl.ScreenMain) {
                HStack {
                    searchTabIos.renderMainScreen()
                }
            } else if (screen is Solution_tabs_implSolutionTabsImpl.ScreenOrders) {
                orderIos.renderAllOrders()
            } else if (screen is Solution_tabs_implSolutionTabsImpl.ScreenSettings) {
                settingsIos.renderSettings()
            }
            Spacer()
            renderBottomNavigation()
        }.padding()

    }

}

func todoState(state: Solution_tabs_implSolutionTabsImpl.State) {
    let ut = TypeSolutionTabs()
    let v = ut.actionSelectOrders
}
