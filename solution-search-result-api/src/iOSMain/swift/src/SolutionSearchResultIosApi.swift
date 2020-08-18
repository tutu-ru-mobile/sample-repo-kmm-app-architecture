import SwiftUI
import Foundation
import app_di

public protocol SolutionSearchResultIosApi {
    associatedtype V1: View
    func renderSearchResult() -> V1
}
