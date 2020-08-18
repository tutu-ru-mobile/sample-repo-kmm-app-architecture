import SwiftUI
import Foundation
import app_di

public protocol SolutionAuthIosApi {
    associatedtype V1: View
    func renderLoginForm() -> V1
}
