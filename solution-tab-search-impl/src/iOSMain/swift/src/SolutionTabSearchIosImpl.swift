import SwiftUI
import Foundation
import app_di
import lib_basic_swift
import solution_tab_search_api_swift
import solution_search_form_api_swift
import solution_search_start_api_swift
import solution_search_result_api_swift
import solution_buy_api_swift

public struct SolutionTabSearchIosImpl
        <
        TSolutionSearchFormIosApi: SolutionSearchFormIosApi,
        TSolutionSearchStartIosApi: SolutionSearchStartIosApi,
        TSolutionSearchResultIosApi: SolutionSearchResultIosApi,
        TSolutionBuyIosApi: SolutionBuyIosApi
        >: SolutionTabSearchIosApi {

    private var common: Solution_tab_search_implSolutionTabSearchImpl
    private var searchForm: TSolutionSearchFormIosApi
    private var searchStart: TSolutionSearchStartIosApi
    private var searchResult: TSolutionSearchResultIosApi
    private var solutionBuyIos: TSolutionBuyIosApi

    public init(
            common: Solution_tab_search_implSolutionTabSearchImpl,
            searchForm: TSolutionSearchFormIosApi,
            searchStart: TSolutionSearchStartIosApi,
            searchResult: TSolutionSearchResultIosApi,
            solutionBuyIos: TSolutionBuyIosApi
    ) {
        self.common = common
        self.searchForm = searchForm
        self.searchStart = searchStart
        self.searchResult = searchResult
        self.solutionBuyIos = solutionBuyIos
    }

    public func renderMainScreen() -> some View {
        HStack {
            if(common.isSearchForm()) {
                searchForm.renderSearchForm()
            }
            if(common.isSearchStart()) {
                searchStart.renderSearchStart()
            }
            if(common.isSearchResult()) {
                searchResult.renderSearchResult()
            }
            if(common.isBuy()) {
                solutionBuyIos.renderBuy()
            }
        }
    }

}
