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
    lazy public var searchForm = SolutionSearchFormIosImpl()
    lazy public var searchStart = SolutionSearchStartIosImpl()
    lazy public var searchResult = SolutionSearchResultIosImpl()
    lazy public var searchTab = SolutionTabSearchIosImpl(
            common: common.solutionTabSearch,
            searchForm: searchForm,
            searchStart: searchStart,
            searchResult: searchResult
    )
    lazy public var tabs = SolutionTabsIOSImpl(
            common: common.solutionTabs,
            searchTabIos: searchTab
    )

    public init() {

    }
}
