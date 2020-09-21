package com.sample

import react.RBuilder
import react.dom.*

class SolutionTabsBrowserImpl(
    val commonImpl: SolutionTabsImpl
//    val searchTabBrowser: SolutionTabSearchBrowserApi,
//    val ordersTabBrowser: SolutionOrderBrowserApi,
//    val settingsTabBrowser: SolutionSettingsApiBrowser
) /*: SolutionTabsBrowserApi*/ {

    /*override*/ fun renderScaffold(react: RBuilder) = react.apply {
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
//        Scaffold(
//            bottomBar = {
//                renderBottomNavigation()
//            }
//        ) {
//            DefaultPadding {
//                when (commonImpl.store.state.screen) {
//                    is SolutionTabsImpl.Screen.Main -> {
//                        searchTabBrowser.renderMainScreen()
//                    }
//                    is SolutionTabsImpl.Screen.Orders -> {
//                        Column(modifier = Modifier.padding(16.dp)) {
//                            ordersTabBrowser.renderAllOrders()
//                        }
//                    }
//                    is SolutionTabsImpl.Screen.Settings -> {
//                        Column(modifier = Modifier.padding(16.dp)) {
//                            settingsTabBrowser.renderSettings()
//                        }
//                    }
//                }
//            }
//        }
    }

}
