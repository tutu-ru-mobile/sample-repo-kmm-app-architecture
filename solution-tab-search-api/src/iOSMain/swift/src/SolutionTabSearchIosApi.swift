import SwiftUI
import Foundation
import app_di

public protocol SolutionTabSearchIosApi {
    associatedtype V1: View
    func renderMainScreen() -> V1
}
