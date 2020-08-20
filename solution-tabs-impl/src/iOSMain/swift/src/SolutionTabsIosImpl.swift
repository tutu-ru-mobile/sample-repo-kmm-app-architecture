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
                self.common.actionSelectMain()
            }) {
                Text("Main")
                        .foregroundColor(self.common.isSelectedMain() ? Color.black : Color.blue)
                        .colorRect(color: self.common.color)
            }.padding()
            Button(action: {
                self.common.actionSelectOrders()
            }) {
                Text("Orders")
                        .foregroundColor(self.common.isSelectedOrders() ? Color.black : Color.blue)
                        .colorRect(color: self.common.color)
            }.padding()
            Button(action: {
                self.common.actionSelectSettings()
            }) {
                Text("Settings")
                        .foregroundColor(self.common.isSelectedSettings() ? Color.black : Color.blue)
                        .colorRect(color: self.common.color)
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
