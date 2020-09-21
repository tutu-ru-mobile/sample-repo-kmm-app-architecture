package com.sample

import react.RBuilder
import react.dom.h2

class SolutionTabSearchBrowserImpl(
    val common: SolutionTabSearchImpl
//    val searchForm: SolutionSearchFormBrowserApi,
//    val searchStart: SolutionSearchStartBrowserApi,
//    val searchResult: SolutionSearchResultBrowserApi,
//    val solutionBuy: SolutionBuyBrowserApi
) : SolutionTabSearchBrowserApi {

    override fun renderMainScreen(react: RBuilder) {
        react.apply {
            h2 {
                +"renderMainScreen"
            }
        }
        val last = common.store.state.backStack.last()
        when (last.event) {
//            is SolutionSearchFormApi.NavSearchForm -> {
//                searchForm.renderSearchForm()
//            }
//            is SolutionSearchStartApi.NavSearchStart -> {
//                searchStart.renderSearching()
//            }
//            is SolutionSearchResultApi.NavSearchResultEvent -> {
//                searchResult.renderSearchResult()
//            }
//            is SolutionBuyApi.NavBuyEvent -> {
//                solutionBuy.renderBuy()
//            }
        }
    }

}
