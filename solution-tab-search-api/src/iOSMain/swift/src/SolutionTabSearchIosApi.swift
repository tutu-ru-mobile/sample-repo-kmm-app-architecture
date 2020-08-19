import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionTabSearchIosApi {
    associatedtype V1: View
    func renderMainScreen() -> V1
}
