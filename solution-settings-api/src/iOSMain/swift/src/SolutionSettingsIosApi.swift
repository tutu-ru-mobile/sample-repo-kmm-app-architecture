import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionSettingsIosApi {
    associatedtype V1: View
    func renderSettings() -> V1
}
