package com.sample

import kotlin.random.Random

class SolutionTabsTelegramImpl(
    val commonImpl: SolutionTabsImpl,
    val searchTabTelegram: SolutionTabSearchTelegramApi,
    val ordersTabTelegram: SolutionOrderTelegramApi
) : SolutionTabsTelegramApi {

    override fun renderScaffold(): TelegramScaffold {
        return TelegramScaffold(
            content = when (commonImpl.store.state.screen) {
                is SolutionTabsImpl.Screen.Main -> {
                    searchTabTelegram.renderMainScreen()
                }
                is SolutionTabsImpl.Screen.Orders -> {
                    ordersTabTelegram.renderAllOrders()
                }
                is SolutionTabsImpl.Screen.Settings -> {
                    TelegramView.Message(
                        """
                            Экран настроек
                            Для Телеграма тут пока пусто
                        """.trimIndent()
                    )
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
