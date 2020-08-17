import SwiftUI
import Foundation
import app_di
import solution_tabs_impl_swift
import solution_tab_search_impl_swift

public class AppDiIos {
    public let common = AppDi()
    public var tabs:SolutionTabsIOSImpl
    public var searchTab:SolutionTabSearchIosImpl

    public init() {
        searchTab = SolutionTabSearchIosImpl()
        tabs = SolutionTabsIOSImpl(
                common: common.mainNav,
                searchTabIos: searchTab
                )
    }

}
