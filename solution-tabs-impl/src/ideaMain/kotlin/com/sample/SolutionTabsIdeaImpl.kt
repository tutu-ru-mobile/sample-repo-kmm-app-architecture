package com.sample

class SolutionTabsIdeaImpl(
    val commonImpl: SolutionTabsImpl
//    val searchTabTelegram: SolutionTabSearchTelegramApi,
//    val ordersTabTelegram: SolutionOrderTelegramApi
////    val settingsTabAndroid: SolutionSettingsApiAndroid
) : SolutionTabsIdeaApi {

    override fun renderScaffold(): IdeaPanel {
        return panelView {
            val state = commonImpl.store.state
            when (state.screen) {
                is SolutionTabsImpl.Screen.Main -> {
                    label("SolutionTabsImpl.Screen.Main")
                }
                is SolutionTabsImpl.Screen.Orders -> {
                    label("SolutionTabsImpl.Screen.Orders")
                }
                is SolutionTabsImpl.Screen.Settings -> {
                    label("SolutionTabsImpl.Screen.Settings")
                }
            }
            label("todo")
            row {
                button("Поиск") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                }
                button("Мои Билеты") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                }
                button("Настройки") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
                }
            }
        }
    }

}
