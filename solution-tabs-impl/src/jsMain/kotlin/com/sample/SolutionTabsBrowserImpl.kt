package com.sample

import kotlinx.css.Align
import kotlinx.css.alignContent
import kotlinx.css.alignItems
import kotlinx.css.alignSelf
import react.RBuilder
import react.dom.*
import react.dom.div
import styled.css
import styled.styledDiv

class SolutionTabsBrowserImpl(
    val commonImpl: SolutionTabsImpl,
    val searchTabBrowser: SolutionTabSearchBrowserApi,
    val ordersTabBrowser: SolutionOrderBrowserApi,
    val settingsTabBrowser: SolutionSettingsBrowserApi
) : SolutionTabsBrowserApi {

    override fun renderScaffold(react: RBuilder) {
        react.apply {

            div {
                when (commonImpl.store.state.screen) {
                    is SolutionTabsImpl.Screen.Main -> {
                        searchTabBrowser.renderMainScreen(this)
                    }
                    is SolutionTabsImpl.Screen.Orders -> {
                        ordersTabBrowser.renderAllOrders(this)
                    }
                    is SolutionTabsImpl.Screen.Settings -> {
                        settingsTabBrowser.renderSettings(this)
                    }
                }
            }
            br {}
            br {}
            div {
                btn("Поиск") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                }
                btn("Мои билеты") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                }
                btn("Настройки") {
                    commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
                }
            }


        }
    }

}
