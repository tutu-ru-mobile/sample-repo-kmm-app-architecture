package com.sample

class AppDiTelegram(
    val common: AppDi,
    val paymentPoll: () -> Unit,
    val inputStr: (text: String, callback: (String) -> Unit) -> Unit
) {
    val solutionTabsTelegram by lazy {
        SolutionTabsTelegramImpl(
            commonImpl = common.solutionTabs,
            searchTabTelegram = searchTabTelegram,
            ordersTabTelegram = solutionOrder
        )
    }

    val solutionOrder by lazy {
        SolutionOrderTelegramImpl(
            common.solutionOrder
        )
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
        SolutionSearchFormTelegramImpl(
            common.solutionSearchForm,
            inputStr = inputStr
        )
    }

    val solutionBuy by lazy {
        SolutionBuyTelegramImpl(common.solutionBuy, paymentPoll)
    }

}
