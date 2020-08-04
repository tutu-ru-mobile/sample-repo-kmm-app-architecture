package com.sample

import androidx.compose.Composable

class SolutionTabSearchAndroidImpl(
    val common: SolutionTabSearchImpl,
    val searchForm: SolutionSearchFormAndroidApi,
    val startSearch: SolutionSearchStartAndroidApi,
    val searchResult: SolutionSearchResultAndroidApi
) : SolutionTabSearchAndroidApi {

    @Composable
    override fun renderMainScreen() {
        when (common.store.state.screen) {
            is SolutionTabSearchImpl.Screen.SearchForm -> {
                searchForm.renderSearchForm()
            }
            is SolutionTabSearchImpl.Screen.StartSearch -> {
                startSearch.renderSearching()
            }
            is SolutionTabSearchImpl.Screen.SearchResult -> {
                searchResult.renderSearchResult()
            }
        }
    }

}
