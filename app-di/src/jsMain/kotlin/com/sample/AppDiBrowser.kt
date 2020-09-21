package com.sample

class AppDiBrowser(val common: AppDi) {
    val searchTabBrowser by lazy {
        SolutionTabSearchBrowserImpl(common.solutionTabSearch)
    }
    val ordersTabBrowser by lazy {
        SolutionOrderBrowserImpl(common.solutionOrder)
    }

    val settingsTabBrowser by lazy {
        SolutionSettingsAndroidImpl(common.solutionSettings)
    }

    val solutionTabsBrowser by lazy {
        SolutionTabsBrowserImpl(
            common.solutionTabs,
            searchTabBrowser,
            ordersTabBrowser,
            settingsTabBrowser
        )
    }
}

