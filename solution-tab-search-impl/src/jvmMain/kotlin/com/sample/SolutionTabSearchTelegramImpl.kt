package com.sample

class SolutionTabSearchTelegramImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormTelegramApi,
    val searchStart: SolutionSearchStartTelegramApi,
    val searchResult: SolutionSearchResultTelegramApi
//    val solutionBuy: SolutionBuyAndroidApi
) : SolutionTabSearchTelegramApi {

    override fun renderMainScreen(): Content {
        val last = common.store.state.backStack.last()
        return when (last.event) {
            is SolutionSearchFormApi.NavSearchForm -> {
                searchForm.renderSearchForm()
            }
            is SolutionSearchStartApi.NavSearchStart -> {
                searchStart.renderSearching()
            }
            is SolutionSearchResultApi.NavSearchResultEvent -> {
                searchResult.renderSearchResult()
            }
            is SolutionBuyApi.NavBuyEvent -> {
                Content.Message("solutionBuy.renderBuy()")
//                solutionBuy.renderBuy()
            }
            else -> {
                Content.Message("renderMainScreen")//todo
            }
        }
    }

}
