import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionOrderIosApi {
    associatedtype V1: View
    associatedtype V2: View
    func renderAllOrders() -> V1
    func renderNearestOrder() -> V2
}
