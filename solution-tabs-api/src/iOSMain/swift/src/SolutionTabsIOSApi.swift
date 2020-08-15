import SwiftUI
import Foundation
import app_di

public protocol SolutionTabsIOSApi {
    associatedtype V1: View
    associatedtype V2: View

    func renderBottomNavigation() -> V1
    func renderScaffold() -> V2
}
