import SwiftUI
import Foundation
import app_di

public protocol SolutionOrderIosApi {
    associatedtype V1: View
    func todoRender() -> V1
}