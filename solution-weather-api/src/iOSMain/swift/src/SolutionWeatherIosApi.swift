import SwiftUI
import Foundation
import app_di
import lib_basic_swift
public protocol SolutionWeatherIosApi {
    associatedtype V1: View
    func renderWeather() -> V1
}
