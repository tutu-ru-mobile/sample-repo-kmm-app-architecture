import SwiftUI
import Foundation
import app_di

public protocol SolutionWalletIosApi {
    associatedtype V1: View
    associatedtype V2: View
    func renderBonusesAndRefillButton() -> V1
    func renderBonusCount() -> V2
}
