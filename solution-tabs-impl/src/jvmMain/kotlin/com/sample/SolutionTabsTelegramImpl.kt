package com.sample

import kotlin.random.Random

class SolutionTabsTelegramImpl(
    val commonImpl: SolutionTabsImpl,
    val searchTabTelegram: SolutionTabSearchTelegramApi
//    val ordersTabAndroid: SolutionOrderAndroidApi,
//    val settingsTabAndroid: SolutionSettingsApiAndroid
) : SolutionTabsTelegramApi {

    override fun renderScaffold(): TelegramScaffold {
        return TelegramScaffold(
            content = when (commonImpl.store.state.screen) {
                is SolutionTabsImpl.Screen.Main -> {
                    searchTabTelegram.renderMainScreen()
                }
                is SolutionTabsImpl.Screen.Orders -> {
                    Content.Message(Random.nextInt().toString())
                }
                is SolutionTabsImpl.Screen.Settings -> {
                    Content.Message(Random.nextInt().toString())
                }
            },
            navButtons = listOf(
                //todo selection commonImpl.store.state.screen == SolutionTabsImpl.Screen.Main
                Content.Button("Поиск") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                },
                Content.Button("Мои билеты") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                },
                Content.Button("Настройки") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
                }
            )
        )
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

    fun renderBottomNavigation() {
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
//                    onSelect = {
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
//                    onSelect = {
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
//                    onSelect = {
//                        commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
//                    }
//                )
//            }
//        }
    }

}
