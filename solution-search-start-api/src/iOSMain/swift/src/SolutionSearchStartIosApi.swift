import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionSearchStartIosApi {
    associatedtype V1: View
    func renderSearchStart() -> V1
}
