import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionSettingsIosApi {
    associatedtype V1: View
    func renderSettings() -> V1
}
