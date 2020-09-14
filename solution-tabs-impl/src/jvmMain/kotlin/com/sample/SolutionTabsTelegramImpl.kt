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
                    TelegramView.Message(Random.nextInt().toString())
                }
                is SolutionTabsImpl.Screen.Settings -> {
                    TelegramView.Message(Random.nextInt().toString())
                }
            },
            navButtons = listOf(
                TelegramButton("Поиск") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                },
                TelegramButton("Мои билеты") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                },
                TelegramButton("Настройки") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
                }
            )
        )
    }

}
