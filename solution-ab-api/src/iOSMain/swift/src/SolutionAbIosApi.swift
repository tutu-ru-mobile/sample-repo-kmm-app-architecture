import SwiftUI
import Foundation
import app_di

public protocol SolutionAbIosApi {
    associatedtype V1: View
    func renderAbSettings() -> V1
}
