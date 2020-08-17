import SwiftUI
import Foundation
import app_di
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
    private var startSearch: TSolutionSearchStartIosApi
    private var searchResult: TSolutionSearchResultIosApi

    public init(
            common: Solution_tab_search_implSolutionTabSearchImpl,
            searchForm: TSolutionSearchFormIosApi,
            searchStart: TSolutionSearchStartIosApi,
            searchResult: TSolutionSearchResultIosApi
    ) {
        self.common = common
        self.searchForm = searchForm
        self.startSearch = searchStart
        self.searchResult = searchResult
    }

    public func renderMainScreen() -> some View {
        HStack {
            Text("todo renderMainScreen")
        }
    }

}
