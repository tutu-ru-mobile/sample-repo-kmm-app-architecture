import SwiftUI
import Foundation
import app_di
import solution_ab_api_swift
import solution_ab_impl_swift
import solution_attention_api_swift
import solution_attention_impl_swift
import solution_auth_api_swift
import solution_auth_impl_swift
import solution_order_api_swift
import solution_order_impl_swift
import solution_search_form_api_swift
import solution_search_form_impl_swift
import solution_search_result_api_swift
import solution_search_result_impl_swift
import solution_search_start_api_swift
import solution_search_start_impl_swift
import solution_settings_api_swift
import solution_settings_impl_swift
import solution_tab_search_api_swift
import solution_tab_search_impl_swift
import solution_tabs_api_swift
import solution_tabs_impl_swift
import solution_wallet_api_swift
import solution_wallet_impl_swift
import solution_weather_api_swift
import solution_weather_impl_swift

public class AppDiIos {
    public let common = AppDi()
    lazy public var solutionSearchFormIos = SolutionSearchFormIosImpl(
            common: common.solutionSearchForm
    )
    lazy public var solutionSearchStartIos = SolutionSearchStartIosImpl()
    lazy public var solutionSearchResultIos = SolutionSearchResultIosImpl(
            common: common.solutionSearchResult,
            searchStart: common.solutionSearchStart
    )
    lazy public var solutionTabSearch = SolutionTabSearchIosImpl(
            common: common.solutionTabSearch,
            searchForm: solutionSearchFormIos,
            searchStart: solutionSearchStartIos,
            searchResult: solutionSearchResultIos
    )
    lazy public var solutionTabs = SolutionTabsIosImpl(
            common: common.solutionTabs,
            tabSearchIos: solutionTabSearch,
            orderIos: solutionOrderIos,
            settingsIos: solutionSettingsIos
    )
    lazy public var solutionWeatherIos = SolutionWeatherIosImpl()
    lazy public var solutionWalletIos = SolutionWalletIosImpl()
    lazy public var solutionSettingsIos = SolutionSettingsIosImpl()
    lazy public var solutionOrderIos = SolutionOrderIosImpl(
            common: common.solutionOrder
    )
    lazy public var solutionAbIos = SolutionAbIosImpl()
    lazy public var solutionAttentionIos = SolutionAttentionIosImpl()
    lazy public var solutionAuthIos = SolutionAuthIosImpl()

    public init() {

    }
}
