
package com.sample

import kotlin.random.Random

class SolutionTabsIdeaImpl(
    val commonImpl: SolutionTabsImpl
//    val searchTabTelegram: SolutionTabSearchTelegramApi,
//    val ordersTabTelegram: SolutionOrderTelegramApi
////    val settingsTabAndroid: SolutionSettingsApiAndroid
) : SolutionTabsIdeaApi {

    override fun renderScaffold(): IdeaPanel {
        return panelView {
            label("todo")
        }
    }

}
