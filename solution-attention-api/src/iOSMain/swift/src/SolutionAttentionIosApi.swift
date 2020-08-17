import SwiftUI
import Foundation
import app_di

public protocol SolutionAttentionIosApi {
    associatedtype V1: View
    func todoRender() -> V1
}
