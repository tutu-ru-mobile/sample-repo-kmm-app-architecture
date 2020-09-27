package com.sample

class SolutionTabsConsoleImpl(
    val commonImpl: SolutionTabsImpl,
    val searchTabConsole: SolutionTabSearchConsoleApi,
    val ordersTabConsole: SolutionOrderConsoleApi,
    val settingsTabConsole: SolutionSettingsConsoleApi
) : SolutionTabsConsoleApi {

    override fun renderScaffold(): ConsolePanel {
        return consolePanelView {
            val state = commonImpl.store.state
            when (state.screen) {
                is SolutionTabsImpl.Screen.Main -> {
                    searchTabConsole.renderMainScreen(this)
                }
                is SolutionTabsImpl.Screen.Orders -> {
                    ordersTabConsole.renderAllOrders(this)
                }
                is SolutionTabsImpl.Screen.Settings -> {
                    settingsTabConsole.renderSettings(this)
                }
            }
            bottomRow {
                button("Поиск", "q", state.screen == SolutionTabsImpl.Screen.Main) {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                }
                button("Мои Билеты", "w", state.screen == SolutionTabsImpl.Screen.Orders) {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                }
                button("Настройки", "e", state.screen == SolutionTabsImpl.Screen.Settings) {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
                }
            }
        }
    }

}
