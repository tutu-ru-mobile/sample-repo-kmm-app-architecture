package com.sample



class AppDiIdea(getGithubMail: ((String) -> Unit) -> Unit) {
    val authIdea by lazy {
        SolutionAuthIdeaImpl(
            getGithubMail
        )
    }
    val common by lazy {
        AppDiAbstract(authIdea)
    }

    val ordersTabIdea by lazy {
        SolutionOrderIdeaImpl(common.solutionOrder)
    }
    val weatherIdea by lazy {
        SolutionWeatherIdeaImpl(
            commonImpl = common.solutionWeather
        )
    }
    val solutionBonusIdea by lazy {
        SolutionBonusIdeaImpl(common.solutionBonus)
    }
    val attentionIdea by lazy {
        SolutionAttentionIdeaImpl(
            commonImpl = common.solutionAttention,
            solutionAuth = common.solutionAuth,
            weatherIdea = weatherIdea,
            ab = common.solutionAb,
            orderIdea = ordersTabIdea,
            solutionBonusIdea = solutionBonusIdea
        )
    }
    val searchForm by lazy {
        SolutionSearchFormIdeaImpl(
            commonImpl = common.solutionSearchForm,
            attentionIdea = attentionIdea
        )
    }
    val searchResult by lazy {
        SolutionSearchResultIdeaImpl(
            common = common.solutionSearchResult,
            searchStart = common.solutionSearchStart
        )
    }
    val searchStart by lazy {
        SolutionSearchStartIdeaImpl(
            common = common.solutionSearchStart
        )
    }
    val solutionBuy by lazy {
        SolutionBuyIdeaImpl(
            commonImpl = common.solutionBuy,
            solutionBonusIdea = solutionBonusIdea,
            solutionBonus = common.solutionBonus
        )
    }
    val searchTabIdea by lazy {
        SolutionTabSearchIdeaImpl(
            common.solutionTabSearch,
            searchForm = searchForm,
            searchResult = searchResult,
            searchStart = searchStart,
            solutionBuy = solutionBuy
        )
    }
    val ab by lazy {
        SolutionAbIdeaImpl(
            common.solutionAb
        )
    }
    val settingsTabIdea by lazy {
        SolutionSettingsIdeaImpl(
            commonImpl = common.solutionSettings,
            authIdea = authIdea,
            bonus = solutionBonusIdea,
            ab = ab
        )
    }
    val solutionTabsIdea by lazy {
        SolutionTabsIdeaImpl(
            commonImpl = common.solutionTabs,
            ordersTabIdea = ordersTabIdea,
            searchTabIdea = searchTabIdea,
            settingsTabIdea = settingsTabIdea
        )
    }
}
