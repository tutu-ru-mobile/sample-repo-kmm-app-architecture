package com.sample

class AppDiTelegram(private val common: AppDi) {
    val solutionTabsTelegram by lazy {
        SolutionTabsTelegramImpl(common.solutionTabs, searchTabTelegram)
    }

    val searchTabTelegram by lazy {
        SolutionTabSearchTelegramImpl(
            common.solutionTabSearch,
            searchForm = searchFrom,
            searchStart = searchStart,
            searchResult = searchResult,
            solutionBuy = solutionBuy
        )
    }

    val searchStart by lazy {
        SolutionSearchStartTelegramImpl(common.solutionSearchStart)
    }

    val searchResult by lazy {
        SolutionSearchResultTelegramImpl(common.solutionSearchResult, common.solutionSearchStart)
    }

    val searchFrom by lazy {
        SolutionSearchFormTelegramImpl(SolutionSearchFormImpl(common.solutionSearchStart))
    }

    val solutionBuy by lazy {
        SolutionBuyTelegramImpl(common.solutionBuy)
    }

}
