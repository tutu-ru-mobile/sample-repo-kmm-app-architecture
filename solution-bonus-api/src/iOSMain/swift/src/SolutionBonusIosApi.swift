import SwiftUI
import Foundation
import app_di
import lib_basic_swift

public protocol SolutionBonusIosApi {
    associatedtype V1: View
    associatedtype V2: View
    associatedtype V3: View
    func renderBonusesAndRefillButton() -> V1
    func renderBonusCount() -> V2
    func renderBonusToggle() -> V3
}
