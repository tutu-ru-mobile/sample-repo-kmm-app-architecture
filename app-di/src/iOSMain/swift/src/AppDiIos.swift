import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
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
import solution_bonus_api_swift
import solution_bonus_impl_swift
import solution_buy_api_swift
import solution_buy_impl_swift
import solution_weather_api_swift
import solution_weather_impl_swift

public class AppDiIos {
    public let common = App_diAppDi()
    lazy public var solutionSearchFormIos = SolutionSearchFormIosImpl(
            common: common.solutionSearchForm,
            attentionIos: solutionAttentionIos
    )
    lazy public var solutionSearchStartIos = SolutionSearchStartIosImpl()
    lazy public var solutionBuyIos = SolutionBuyIosImpl(
            common: common.solutionBuy,
            solutionBonus: common.solutionBonus,
            solutionBonusIos: solutionBonusIos
    )
    lazy public var solutionSearchResultIos = SolutionSearchResultIosImpl(
            common: common.solutionSearchResult,
            searchStart: common.solutionSearchStart
    )
    lazy public var solutionTabSearch = SolutionTabSearchIosImpl(
            common: common.solutionTabSearch,
            searchForm: solutionSearchFormIos,
            searchStart: solutionSearchStartIos,
            searchResult: solutionSearchResultIos,
            solutionBuyIos: solutionBuyIos
    )
    lazy public var solutionTabs = SolutionTabsIosImpl(
            common: common.solutionTabs,
            tabSearchIos: solutionTabSearch,
            orderIos: solutionOrderIos,
            settingsIos: solutionSettingsIos
    )
    lazy public var solutionWeatherIos = SolutionWeatherIosImpl(
            common: common.solutionWeather
    )
    lazy public var solutionBonusIos = SolutionBonusIosImpl(
            common: common.solutionBonus
    )
    lazy public var solutionSettingsIos = SolutionSettingsIosImpl(
            common: common.solutionSettings,
            authIos: solutionAuthIos,
            bonusIos: solutionBonusIos,
            abIos: solutionAbIos
    )
    lazy public var solutionOrderIos = SolutionOrderIosImpl(
            common: common.solutionOrder,
            authIos: solutionAuthIos
    )
    lazy public var solutionAbIos = SolutionAbIosImpl(
            common: common.solutionAb
    )
    lazy public var solutionAttentionIos = SolutionAttentionIosImpl(
            common: common.solutionAttention,
            auth: common.solutionAuth,
            ab: common.solutionAb,
            weatherIos: solutionWeatherIos,
            orderIos: solutionOrderIos,
            bonusIos: solutionBonusIos,
            authIos: solutionAuthIos
    )
    lazy public var solutionAuthIos = SolutionAuthIosImpl(
            common: common.solutionAuth
    )

    public init() {

    }
}
