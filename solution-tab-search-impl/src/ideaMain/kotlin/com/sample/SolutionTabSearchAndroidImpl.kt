package com.sample

class SolutionTabSearchIdeaImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormIdeaApi,
    val searchStart: SolutionSearchStartIdeaApi,
    val searchResult: SolutionSearchResultIdeaApi,
    val solutionBuy: SolutionBuyIdeaApi
) : SolutionTabSearchIdeaApi {

    override fun renderMainScreen(builder: IdeaPanelBuilder) {
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
