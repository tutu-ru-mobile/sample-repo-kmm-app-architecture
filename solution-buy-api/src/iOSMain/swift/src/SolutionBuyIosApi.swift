import SwiftUI
import Foundation
import app_di
import lib_basic_swift

public protocol SolutionBuyIosApi {
    associatedtype V1: View
    func renderBuy() -> V1
}
