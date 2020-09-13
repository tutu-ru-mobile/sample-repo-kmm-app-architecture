package com.sample

class SolutionTabSearchTelegramImpl(
    val common: SolutionTabSearchImpl
//    val searchForm: SolutionSearchFormAndroidApi,
//    val searchStart: SolutionSearchStartAndroidApi,
//    val searchResult: SolutionSearchResultAndroidApi,
//    val solutionBuy: SolutionBuyAndroidApi
) : SolutionTabSearchTelegramApi {

    override fun renderMainScreen(): Content {
        val last = common.store.state.backStack.last()
//        when (last.event) {
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
//        }
        return Content.Message("renderMainScreen")
    }

}
