import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift

public protocol SolutionBuyIosApi {
    associatedtype V1: View
    func renderBuy() -> V1
}
