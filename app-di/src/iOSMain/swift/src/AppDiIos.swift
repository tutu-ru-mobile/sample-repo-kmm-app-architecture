import SwiftUI
import Foundation
import app_di
import solution_tabs_impl_swift
import solution_tab_search_impl_swift

public class AppDiIos {
    public let common = AppDi()
    lazy public var searchTab = SolutionTabSearchIosImpl()
    lazy public var tabs = SolutionTabsIOSImpl(
            common: common.mainNav,
            searchTabIos: searchTab
    )

    public init() {

    }
}
