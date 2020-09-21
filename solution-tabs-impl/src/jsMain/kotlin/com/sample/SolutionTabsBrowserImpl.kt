package com.sample

import react.RBuilder
import react.dom.*

class SolutionTabsBrowserImpl(
    val commonImpl: SolutionTabsImpl,
    val searchTabBrowser: SolutionTabSearchBrowserApi,
    val ordersTabBrowser: SolutionOrderBrowserApi,
    val settingsTabBrowser: SolutionSettingsBrowserApi
) /*: SolutionTabsBrowserApi*/ {

    /*override*/ fun renderScaffold(react: RBuilder) = react.apply {
        div {
            when (commonImpl.store.state.screen) {
                is SolutionTabsImpl.Screen.Main -> {
                    searchTabBrowser.renderMainScreen(this)
                }
                is SolutionTabsImpl.Screen.Orders -> {
                    ordersTabBrowser.renderAllOrders(this)
                }
                is SolutionTabsImpl.Screen.Settings -> {
                    settingsTabBrowser.renderSettings(this)
                }
            }
        }
        table {
            tr {
                td {
                    //selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Main
                    btn("Поиск") {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                    }
                }
                td {
                    btn("Мои билеты") {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                    }
                }
                td {
                    btn("Настройки") {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
                    }
                }
            }
        }
    }

}
