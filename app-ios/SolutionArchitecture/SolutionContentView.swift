import SwiftUI
import app_di
import app_di_swift
import Foundation

struct SolutionContentView: View {

    let appDiIos = AppDiIos()
    @ObservedObject var myViewModel:SolutionViewModel

    init() {
        self.myViewModel = SolutionViewModel(di: appDiIos.common)
    }

    var body: some View {
        VStack {
            Text("updateCount: \(myViewModel.myState.updateCount)")
            appDiIos.tabs.renderScaffold()
        }
    }

}
