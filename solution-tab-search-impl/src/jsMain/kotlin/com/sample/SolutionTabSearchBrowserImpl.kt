package com.sample

import react.RBuilder
import react.dom.div

class SolutionTabSearchBrowserImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormBrowserApi,
    val searchStart: SolutionSearchStartBrowserApi,
    val searchResult: SolutionSearchResultBrowserApi,
    val solutionBuy: SolutionBuyBrowserApi
) : SolutionTabSearchBrowserApi {

    override fun renderMainScreen(react: RBuilder) {
        react.apply {
            div {
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

}
