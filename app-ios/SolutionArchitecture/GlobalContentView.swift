import SwiftUI
import app_di
import app_di_swift
import Foundation

struct GlobalContentView: View {

    let appDiIos = AppDiIos()
    @ObservedObject var myViewModel:GlobalViewModel

    init() {
        self.myViewModel = GlobalViewModel(di: appDiIos.common)
    }

    var body: some View {
        VStack {
            Text("updateCount: \(myViewModel.myState.updateCount)")
            appDiIos.solutionTabs.renderScaffold()
        }
    }

}
