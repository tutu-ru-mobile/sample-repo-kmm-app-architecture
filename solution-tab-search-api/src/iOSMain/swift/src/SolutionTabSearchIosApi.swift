import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionTabSearchIosApi {
    associatedtype V1: View
    func renderMainScreen() -> V1
}
