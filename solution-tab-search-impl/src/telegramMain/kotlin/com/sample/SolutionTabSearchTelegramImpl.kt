package com.sample

class SolutionTabSearchTelegramImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormTelegramApi,
    val searchStart: SolutionSearchStartTelegramApi,
    val searchResult: SolutionSearchResultTelegramApi,
    val solutionBuy: SolutionBuyTelegramApi
) : SolutionTabSearchTelegramApi {

    override fun renderMainScreen(): TelegramView {
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
                solutionBuy.renderBuy()
            }
            else -> {
                throw Error("nav event not implements event: ${last.event}")
            }
        }
    }

}
