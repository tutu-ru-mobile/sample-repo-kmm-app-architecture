import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionAttentionIosApi {
    associatedtype V1: View
    func renderMainScreenAttention() -> V1
}
