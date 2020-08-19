import SwiftUI
import Foundation
import app_di
import lib_basic_swift
import solution_tab_search_api_swift
import solution_search_form_api_swift
import solution_search_start_api_swift
import solution_search_result_api_swift

public struct SolutionTabSearchIosImpl
        <
        TSolutionSearchFormIosApi: SolutionSearchFormIosApi,
        TSolutionSearchStartIosApi: SolutionSearchStartIosApi,
        TSolutionSearchResultIosApi: SolutionSearchResultIosApi
        >: SolutionTabSearchIosApi {

    private var common: Solution_tab_search_implSolutionTabSearchImpl
    private var searchForm: TSolutionSearchFormIosApi
    private var searchStart: TSolutionSearchStartIosApi
    private var searchResult: TSolutionSearchResultIosApi

    public init(
            common: Solution_tab_search_implSolutionTabSearchImpl,
            searchForm: TSolutionSearchFormIosApi,
            searchStart: TSolutionSearchStartIosApi,
            searchResult: TSolutionSearchResultIosApi
    ) {
        self.common = common
        self.searchForm = searchForm
        self.searchStart = searchStart
        self.searchResult = searchResult
    }

    public func renderMainScreen() -> some View {
        HStack {
            if(common.isSearchForm()) {
                searchForm.renderSearchForm()
            }
            if(common.isSearchStart()) {
                searchStart.todoRender()
            }
            if(common.isSearchResult()) {
                searchResult.renderSearchResult()
            }
        }
    }

}
