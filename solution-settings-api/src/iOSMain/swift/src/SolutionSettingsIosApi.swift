import SwiftUI
import Foundation
import app_di

public protocol SolutionSettingsIosApi {
    associatedtype V1: View
    func renderSettings() -> V1
}
