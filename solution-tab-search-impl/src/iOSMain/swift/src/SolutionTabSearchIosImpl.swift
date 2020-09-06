import SwiftUI
import Foundation
import ios_kotlin_pod
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
    private var searchForm: Solution_search_form_apiSolutionSearchFormApi
    private var searchStart: Solution_search_start_apiSolutionSearchStartApi
    private var searchResult: Solution_search_result_apiSolutionSearchResultApi
    private var solutionBuy: Solution_buy_apiSolutionBuyApi
    private var searchFormIos: TSolutionSearchFormIosApi
    private var searchStartIos: TSolutionSearchStartIosApi
    private var searchResultIos: TSolutionSearchResultIosApi
    private var solutionBuyIos: TSolutionBuyIosApi

    public init(
            common: Solution_tab_search_implSolutionTabSearchImpl,
            searchForm: Solution_search_form_apiSolutionSearchFormApi,
            searchStart: Solution_search_start_apiSolutionSearchStartApi,
            searchResult: Solution_search_result_apiSolutionSearchResultApi,
            solutionBuy: Solution_buy_apiSolutionBuyApi,
            searchFormIos: TSolutionSearchFormIosApi,
            searchStartIos: TSolutionSearchStartIosApi,
            searchResultIos: TSolutionSearchResultIosApi,
            solutionBuyIos: TSolutionBuyIosApi
    ) {
        self.common = common
        self.searchForm = searchForm
        self.searchStart = searchStart
        self.searchResult = searchResult
        self.solutionBuy = solutionBuy
        self.searchFormIos = searchFormIos
        self.searchStartIos = searchStartIos
        self.searchResultIos = searchResultIos
        self.solutionBuyIos = solutionBuyIos
    }

    public func renderMainScreen() -> some View {
        VStack {
            if (searchForm.isSearchFormEvent(event: common.getLastEvent())) {
                searchFormIos.renderSearchForm()
            }
            if (searchStart.isNavSearchStartEvent(event: common.getLastEvent())) {
                searchStartIos.renderSearchStart()
            }
            if (searchResult.isNavSearchResultEvent(event: common.getLastEvent())) {
                searchResultIos.renderSearchResult()
            }
            if (solutionBuy.isNavBuyEvent(event: common.getLastEvent())) {
                solutionBuyIos.renderBuy()
            }
        }
    }

}
