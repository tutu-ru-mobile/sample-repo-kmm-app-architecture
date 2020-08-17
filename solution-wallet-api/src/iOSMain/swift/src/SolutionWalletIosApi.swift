import SwiftUI
import Foundation
import app_di

public protocol SolutionWalletIosApi {
    associatedtype V1: View
    func todoRender() -> V1
}
