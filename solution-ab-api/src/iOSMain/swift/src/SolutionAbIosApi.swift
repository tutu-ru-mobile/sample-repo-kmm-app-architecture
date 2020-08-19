import SwiftUI
import Foundation
import app_di
import lib_basic_swift

public protocol SolutionAbIosApi {
    associatedtype V1: View
    func renderAbSettings() -> V1
}
