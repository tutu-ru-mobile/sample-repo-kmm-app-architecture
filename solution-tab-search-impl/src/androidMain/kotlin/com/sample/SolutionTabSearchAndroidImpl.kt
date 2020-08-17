package com.sample

import androidx.compose.Composable

class SolutionTabSearchAndroidImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormAndroidApi,
    val searchStart: SolutionSearchStartAndroidApi,
    val searchResult: SolutionSearchResultAndroidApi
) : SolutionTabSearchAndroidApi {

    @Composable
    override fun renderMainScreen() {
        when (common.store.state.screen) {
            is SolutionTabSearchImpl.Screen.SearchForm -> {
                searchForm.renderSearchForm()
            }
            is SolutionTabSearchImpl.Screen.StartSearch -> {
                searchStart.renderSearching()
            }
            is SolutionTabSearchImpl.Screen.SearchResult -> {
                searchResult.renderSearchResult()
            }
        }
    }

}
