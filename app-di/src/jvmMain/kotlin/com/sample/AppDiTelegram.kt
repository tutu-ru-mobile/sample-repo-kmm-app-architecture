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
            searchResult = searchResult
        )
    }

    val searchStart by lazy {
        SolutionSearchStartTelegramImpl(common.solutionSearchStart)
    }

    val searchResult by lazy {
        SolutionSearchResultTelegramImpl(common.solutionSearchResult)
    }

    val searchFrom by lazy {
        SolutionSearchFormTelegramImpl(SolutionSearchFormImpl(common.solutionSearchStart))
    }

}
