import SwiftUI
import Foundation
import app_di

public func hexColorToSwiftUI(color: Lib_basicHexColor) -> Color {
    Color(UIColor(
            red: CGFloat(color.floatRed),
            green: CGFloat(color.floatGreen),
            blue: CGFloat(color.floatBlue),
            alpha: CGFloat(color.floatAlpha)
    ))
}

public extension Lib_basicHexColor {
    func toSwiftUI() -> Color {
        return hexColorToSwiftUI(color: self)
    }
}

extension View {
    @inlinable public func colorRect(color: Lib_basicHexColor) -> some View {
        self.padding()
            .overlay(
                    RoundedRectangle(cornerRadius: 10)
                            .stroke(color.toSwiftUI(), lineWidth: 1)
            )
    }
}
