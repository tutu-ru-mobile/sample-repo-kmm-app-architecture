import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionSearchResultIosApi {
    associatedtype V1: View
    func renderSearchResult() -> V1
}
