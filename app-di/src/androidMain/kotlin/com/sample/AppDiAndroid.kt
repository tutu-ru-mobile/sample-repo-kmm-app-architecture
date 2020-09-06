package com.sample

class AppDiAndroid(val common: AppDi) {
    val solutionAuthAndroid by lazy { SolutionAuthAndroidImpl(common.solutionAuth) }
    val solutionBonusAndroid by lazy { SolutionBonusAndroidImpl(common.solutionBonus) }
    val solutionOrderAndroid by lazy { SolutionOrderAndroidImpl(common.solutionOrder, solutionAuthAndroid) }
    val solutionSearchStartAndroid by lazy {
        SolutionSearchStartAndroidImpl(
            common = common.solutionSearchStart
        )
    }
    val solutionSearchResultAndroid by lazy {
        SolutionSearchResultAndroidImpl(
            common.solutionSearchResult,
            common.solutionSearchStart
        )
    }
    val solutionWeatherAndroid by lazy {
        SolutionWeatherAndroidImpl(common.solutionWeather)
    }
    val solutionAttentionAndroid by lazy {
        SolutionAttentionAndroidImpl(
            common.solutionAttention,
            common.solutionAuth,
            solutionWeatherAndroid,
            solutionOrderAndroid,
            solutionBonusAndroid,
            common.solutionAb
        )
    }
    val solutionSearchFormAndroid by lazy {
        SolutionSearchFormAndroidImpl(
            common.solutionSearchForm,
            solutionAttentionAndroid
        )
    }
    val solutionTabSearchAndroid by lazy {
        SolutionTabSearchAndroidImpl(
            common = common.solutionTabSearch,
            searchForm = solutionSearchFormAndroid,
            searchStart = solutionSearchStartAndroid,
            searchResult = solutionSearchResultAndroid,
            solutionBuy = solutionBuyAndroid
        )
    }
    val solutionAbAndroid by lazy {
        SolutionAbAndroidImpl(common.solutionAb)
    }
    val solutionSettingsAndroid by lazy {
        SolutionSettingsAndroidImpl(common.solutionSettings, solutionAuthAndroid, solutionBonusAndroid, solutionAbAndroid)
    }
    val solutionTabsAndroid by lazy {
        SolutionTabsAndroidImpl(
            common.solutionTabs,
            solutionTabSearchAndroid,
            solutionOrderAndroid,
            solutionSettingsAndroid
        )
    }
    val solutionBuyAndroid by lazy {
        SolutionBuyAndroidImpl(common.solutionBuy, solutionBonusAndroid, common.solutionBonus)
    }

}
