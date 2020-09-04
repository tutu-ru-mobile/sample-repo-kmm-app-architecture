import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionAttentionIosApi {
    associatedtype V1: View
    func renderMainScreenAttention() -> V1
}
