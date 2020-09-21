package com.sample

import react.RBuilder
import react.dom.*

class SolutionTabsBrowserImpl(
    val commonImpl: SolutionTabsImpl
//    val searchTabAndroid: SolutionTabSearchAndroidApi,
//    val ordersTabAndroid: SolutionOrderAndroidApi,
//    val settingsTabAndroid: SolutionSettingsApiAndroid
) /*: SolutionTabsAndroidApi*/ {

    /*override*/ fun renderScaffold(react: RBuilder) = react.apply {
        h1 {
            +"Hello SolutionTabsBrowserImpl"
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
//        Scaffold(
//            bottomBar = {
//                renderBottomNavigation()
//            }
//        ) {
//            DefaultPadding {
//                when (commonImpl.store.state.screen) {
//                    is SolutionTabsImpl.Screen.Main -> {
//                        searchTabAndroid.renderMainScreen()
//                    }
//                    is SolutionTabsImpl.Screen.Orders -> {
//                        Column(modifier = Modifier.padding(16.dp)) {
//                            ordersTabAndroid.renderAllOrders()
//                        }
//                    }
//                    is SolutionTabsImpl.Screen.Settings -> {
//                        Column(modifier = Modifier.padding(16.dp)) {
//                            settingsTabAndroid.renderSettings()
//                        }
//                    }
//                }
//            }
//        }
    }

}
