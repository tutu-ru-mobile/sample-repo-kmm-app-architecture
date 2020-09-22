package com.sample

class AppDiBrowser(val common: AppDi) {
    val weatherBrowser by lazy {
        SolutionWeatherBrowserImpl(
            common.solutionWeather
        )
    }
    val attentionBrowser by lazy {
        SolutionAttentionBrowserImpl(
            common.solutionAttention,
            solutionAuth = common.solutionAuth,
            weatherBrowser = weatherBrowser,
            orderBrowser = ordersTabBrowser,
            solutionBonusBrowser = solutionBonusBrowser,
            ab = common.solutionAb
        )
    }
    val searchForm by lazy {
        SolutionSearchFormBrowserImpl(
            common.solutionSearchForm, attentionBrowser
        )
    }
    val searchStart by lazy {
        SolutionSearchStartBrowserImpl(
            common = common.solutionSearchStart
        )
    }
    val searchResult by lazy {
        SolutionSearchResultBrowserImpl(
            common = common.solutionSearchResult,
            searchStart = common.solutionSearchStart
        )
    }
    val solutionBonusBrowser by lazy {
        SolutionBonusBrowserImpl(
            common = common.solutionBonus
        )
    }
    val solutionBuy by lazy {
        SolutionBuyBrowserImpl(
            commonImpl = common.solutionBuy,
            solutionBonusBrowser = solutionBonusBrowser,
            solutionBonus = common.solutionBonus
        )
    }
    val searchTabBrowser by lazy {
        SolutionTabSearchBrowserImpl(
            common = common.solutionTabSearch,
            searchForm = searchForm,
            searchStart = searchStart,
            searchResult = searchResult,
            solutionBuy = solutionBuy
        )
    }
    val browserAuth by lazy {
        SolutionAuthBrowserImpl(
            common = common.solutionAuth
        )
    }
    val ordersTabBrowser by lazy {
        SolutionOrderBrowserImpl(
            commonImpl = common.solutionOrder,
            browserAuth = browserAuth
        )
    }
    val ab by lazy {
        SolutionAbBrowserImpl(
            commonImpl = common.solutionAb
        )
    }
    val settingsTabBrowser by lazy {
        SolutionSettingsBrowserImpl(
            commonImpl = common.solutionSettings,
            authBrowser = browserAuth,
            bonus = solutionBonusBrowser,
            ab = ab
        )
    }
    val solutionTabsBrowser by lazy {
        SolutionTabsBrowserImpl(
            commonImpl = common.solutionTabs,
            searchTabBrowser = searchTabBrowser,
            ordersTabBrowser = ordersTabBrowser,
            settingsTabBrowser = settingsTabBrowser
        )
    }
}
