import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionSearchStartIosApi {
    associatedtype V1: View
    func renderSearchStart() -> V1
}
