import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionTabsIOSApi {
    associatedtype V1: View
    associatedtype V2: View

    func renderBottomNavigation() -> V1
    func renderScaffold() -> V2
}
