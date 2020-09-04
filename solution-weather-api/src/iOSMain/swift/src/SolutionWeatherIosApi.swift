import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
public protocol SolutionWeatherIosApi {
    associatedtype V1: View
    func renderWeather() -> V1
}
