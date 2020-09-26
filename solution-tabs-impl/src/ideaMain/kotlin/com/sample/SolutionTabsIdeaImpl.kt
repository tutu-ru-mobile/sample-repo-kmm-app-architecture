package com.sample

class SolutionTabsIdeaImpl(
    val commonImpl: SolutionTabsImpl,
    val searchTabIdea: SolutionTabSearchIdeaApi,
    val ordersTabIdea: SolutionOrderIdeaApi,
    val settingsTabIdea: SolutionSettingsIdeaApi
) : SolutionTabsIdeaApi {

    override fun renderScaffold(): IdeaPanel {
        return panelView {
            val state = commonImpl.store.state
            when (state.screen) {
                is SolutionTabsImpl.Screen.Main -> {
                    searchTabIdea.renderMainScreen(this)
                }
                is SolutionTabsImpl.Screen.Orders -> {
                    ordersTabIdea.renderAllOrders(this)
                }
                is SolutionTabsImpl.Screen.Settings -> {
                    settingsTabIdea.renderSettings(this)
                }
            }
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
