import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionSearchFormIosApi {
    associatedtype V1: View
    func renderSearchForm() -> V1
}
