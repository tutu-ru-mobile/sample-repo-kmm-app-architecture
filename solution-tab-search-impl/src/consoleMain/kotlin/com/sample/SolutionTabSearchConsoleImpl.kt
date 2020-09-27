package com.sample

class SolutionTabSearchConsoleImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormConsoleApi,
    val searchStart: SolutionSearchStartConsoleApi,
    val searchResult: SolutionSearchResultConsoleApi,
    val solutionBuy: SolutionBuyConsoleApi
) : SolutionTabSearchConsoleApi {

    override fun renderMainScreen(builder: ConsolePanelBuilder) {
        builder.apply {
            val last = common.store.state.backStack.last()
            when (last.event) {
                is SolutionSearchFormApi.NavSearchForm -> {
                    searchForm.renderSearchForm(this)
                }
                is SolutionSearchStartApi.NavSearchStart -> {
                    searchStart.renderSearching(this)
                }
                is SolutionSearchResultApi.NavSearchResultEvent -> {
                    searchResult.renderSearchResult(this)
                }
                is SolutionBuyApi.NavBuyEvent -> {
                    solutionBuy.renderBuy(this)
                }
            }
        }

    }

}
