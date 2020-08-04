package com.sample

class AppDiAndroid(val common: AppDi) {
    val authAndroid by lazy { SolutionAuthAndroidImpl(common.auth) }
    val walletAndroid by lazy { SolutionWalletAndroidImpl(common.wallet) }
    val orderAndroid by lazy { SolutionOrderAndroidImpl(common.order, authAndroid) }
    val startSearchAndroid by lazy { SolutionSearchStartAndroidImpl(common.startSearch) }
    val searchResultAndroid by lazy {
        SolutionSearchResultAndroidImpl(
            common.searchResult,
            common.startSearch
        )
    }
    val weatherAndroid by lazy {
        SolutionWeatherAndroidImpl(common.weather)
    }
    val attentionAndroid by lazy {
        SolutionAttentionAndroidImpl(
            common.attention,
            common.auth,
            weatherAndroid,
            orderAndroid,
            walletAndroid,
            common.ab
        )
    }
    val searchFormSolutionAndroid by lazy {
        SolutionSearchFormAndroidImpl(
            common.searchFormSolution,
            attentionAndroid
        )
    }
    val mainTabAndroid by lazy {
        SolutionTabSearchAndroidImpl(
            common.mainTab,
            searchFormSolutionAndroid,
            startSearchAndroid,
            searchResultAndroid
        )
    }
    val abAndroid by lazy {
        SolutionAbAndroidImpl(common.ab)
    }
    val settingsAndroid by lazy {
        SolutionSettingsAndroidImpl(common.settingsScreen, authAndroid, walletAndroid, abAndroid)
    }
    val mainNavAndroid by lazy {
        SolutionTabsAndroidImpl(
            common.mainNav,
            mainTabAndroid,
            orderAndroid,
            settingsAndroid
        )
    }
}

