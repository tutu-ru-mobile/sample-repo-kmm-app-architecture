import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionTabsIOSApi {
    associatedtype V1: View
    associatedtype V2: View

    func renderBottomNavigation() -> V1
    func renderScaffold() -> V2
}
