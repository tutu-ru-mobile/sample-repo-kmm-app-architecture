import SwiftUI
import Foundation
import app_di
import solution_tabs_impl_swift

public class AppDiIos {
    public let common = AppDi()
    public var tabs:SolutionTabsIOSImpl

    public init() {
        tabs = SolutionTabsIOSImpl(common: common.mainNav)
    }

}
