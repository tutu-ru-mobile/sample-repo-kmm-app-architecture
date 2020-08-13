import SwiftUI
import Foundation
//import app_di

public struct StationView : View {
    var station: String
    var clickAction: () -> Void

    public init(station: String, clickAction: @escaping () -> ()) {
        self.station = station
        self.clickAction = clickAction
    }

    public var body: some View {
        HStack {
            Image("ic_bike").resizable()
                    .renderingMode(.template)
                    .foregroundColor(false ? .orange : .green)
                    .frame(width: 32.0, height: 32.0)

            Spacer().frame(width: 16)

            VStack(alignment: .leading) {
                Text(station).font(.headline)
            }
            Button(action: { self.clickAction() }) {
                Text("Click")
            }
        }
    }
}
