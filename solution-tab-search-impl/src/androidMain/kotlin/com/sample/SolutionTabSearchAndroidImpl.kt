package com.sample

import androidx.compose.runtime.Composable

class SolutionTabSearchAndroidImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormAndroidApi,
    val searchStart: SolutionSearchStartAndroidApi,
    val searchResult: SolutionSearchResultAndroidApi,
    val solutionBuy: SolutionBuyAndroidApi
) : SolutionTabSearchAndroidApi {

    @Composable
    override fun renderMainScreen() {
        val last = common.store.state.backStack.last()
        when (last.event) {
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
        }
    }

}
