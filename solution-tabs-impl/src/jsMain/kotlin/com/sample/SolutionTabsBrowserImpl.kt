package com.sample

import react.RBuilder
import react.dom.h1

class SolutionTabsBrowserImpl(
    val commonImpl: SolutionTabsImpl
//    val searchTabAndroid: SolutionTabSearchAndroidApi,
//    val ordersTabAndroid: SolutionOrderAndroidApi,
//    val settingsTabAndroid: SolutionSettingsApiAndroid
) /*: SolutionTabsAndroidApi*/ {

    /*override*/ fun renderScaffold(rBuilder: RBuilder) {
        rBuilder.h1 {
            +"Hello SolutionTabsBrowserImpl"
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

//    @Composable
//    override fun renderBottomNavigation() {
//        WrapColorBox(color = commonImpl.getColor()) {
//            BottomNavigation() {
//                BottomNavigationItem(
//                    icon = {
//                        Icon(asset = Icons.Filled.Search)
//                    },
//                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Main,
//                    label = {
//                        Text("Поиск")
//                    },
//                    alwaysShowLabels = true,
//                    onClick = {
//                        commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
//                    }
//                )
//                BottomNavigationItem(
//                    icon = {
//                        Icon(asset = Icons.Filled.AccountBalanceWallet)
//                    },
//                    label = {
//                        Text("Мои билеты")
//                    },
//                    alwaysShowLabels = true,
//                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Orders,
//                    onClick = {
//                        commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
//                    }
//                )
//                BottomNavigationItem(
//                    icon = {
//                        Icon(asset = Icons.Filled.Menu)
//                    },
//                    label = {
//                        Text("Настройки")
//                    },
//                    alwaysShowLabels = true,
//                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Settings,
//                    onClick = {
//                        commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
//                    }
//                )
//            }
//        }
//    }

}
