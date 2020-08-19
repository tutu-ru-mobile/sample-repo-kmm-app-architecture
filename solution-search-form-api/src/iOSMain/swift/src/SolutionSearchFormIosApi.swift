import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionSearchFormIosApi {
    associatedtype V1: View
    func renderSearchForm() -> V1
}
