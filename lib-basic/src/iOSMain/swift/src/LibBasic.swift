import SwiftUI
import Foundation
import app_di

public protocol SolutionAbIosApi {
    associatedtype V1: View
    func renderAbSettings() -> V1
}

public func kotlinColorToSwiftUI(color: KotlinColor) -> Color {
    Color(UIColor(
            red: CGFloat(color.floatRed),
            green: CGFloat(color.floatGreen),
            blue: CGFloat(color.floatBlue),
            alpha: CGFloat(color.floatAlpha)
    ))
}
