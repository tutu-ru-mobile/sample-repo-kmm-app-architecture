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
    @inlinable public func colorRect(color: Lib_basicHexColor, available:Bool = true) -> some View {
        self.colorRect(color: color.toSwiftUI(), available: available)
    }

    @inlinable public func colorRect(color: Color, available:Bool = true) -> some View {
        self.padding(5)
            .overlay(
                    RoundedRectangle(cornerRadius: 0)
                            .stroke(color, lineWidth: available ? 4 : 0)
            )
            .padding(5)
    }
}

public struct MyToggleView: View {
    var label: String
    var onChange: (Bool) -> Void
    var value: Bool

    public init(label: String, value: Bool, onChange: @escaping (Bool) -> ()) {
        self.label = label
        self.onChange = onChange
        self.value = value
    }

    func getBoundValue() -> Binding<Bool> {
        Binding<Bool>(get: { () -> Bool in
            self.value
        }, set: { s in
            self.onChange(s)
        })
    }

    public var body: some View {
        Toggle(isOn: getBoundValue()) {
            Text(label)
        }
    }
}
