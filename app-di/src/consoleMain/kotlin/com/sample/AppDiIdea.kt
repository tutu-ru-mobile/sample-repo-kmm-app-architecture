package com.sample

class AppDiConsole {
    val common by lazy {
        AppDi(/*authConsole*/)
    }
    val authConsole by lazy {
        SolutionAuthConsoleImpl(
            common = common.solutionAuth
        )
    }
    val ordersTabConsole by lazy {
        SolutionOrderConsoleImpl(common.solutionOrder)
    }
    val weatherConsole by lazy {
        SolutionWeatherConsoleImpl(
            commonImpl = common.solutionWeather
        )
    }
    val solutionBonusConsole by lazy {
        SolutionBonusConsoleImpl(common.solutionBonus)
    }
    val attentionConsole by lazy {
        SolutionAttentionConsoleImpl(
            commonImpl = common.solutionAttention,
            solutionAuth = common.solutionAuth,
            weatherConsole = weatherConsole,
            ab = common.solutionAb,
            orderConsole = ordersTabConsole,
            solutionBonusConsole = solutionBonusConsole
        )
    }
    val searchForm by lazy {
        SolutionSearchFormConsoleImpl(
            commonImpl = common.solutionSearchForm,
            attentionConsole = attentionConsole
        )
    }
    val searchResult by lazy {
        SolutionSearchResultConsoleImpl(
            common = common.solutionSearchResult,
            searchStart = common.solutionSearchStart
        )
    }
    val searchStart by lazy {
        SolutionSearchStartConsoleImpl(
            common = common.solutionSearchStart
        )
    }
    val solutionBuy by lazy {
        SolutionBuyConsoleImpl(
            commonImpl = common.solutionBuy,
            solutionBonusConsole = solutionBonusConsole,
            solutionBonus = common.solutionBonus
        )
    }
    val searchTabConsole by lazy {
        SolutionTabSearchConsoleImpl(
            common.solutionTabSearch,
            searchForm = searchForm,
            searchResult = searchResult,
            searchStart = searchStart,
            solutionBuy = solutionBuy
        )
    }
    val ab by lazy {
        SolutionAbConsoleImpl(
            common.solutionAb
        )
    }
    val settingsTabConsole by lazy {
        SolutionSettingsConsoleImpl(
            commonImpl = common.solutionSettings,
            authConsole = authConsole,
            bonus = solutionBonusConsole,
            ab = ab
        )
    }
    val solutionTabsConsole by lazy {
        SolutionTabsConsoleImpl(
            commonImpl = common.solutionTabs,
            ordersTabConsole = ordersTabConsole,
            searchTabConsole = searchTabConsole,
            settingsTabConsole = settingsTabConsole
        )
    }
}
