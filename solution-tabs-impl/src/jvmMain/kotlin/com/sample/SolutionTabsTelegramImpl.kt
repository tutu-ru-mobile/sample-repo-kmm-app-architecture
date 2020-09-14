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
    }

}
