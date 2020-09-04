import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionSearchResultIosApi {
    associatedtype V1: View
    func renderSearchResult() -> V1
}
