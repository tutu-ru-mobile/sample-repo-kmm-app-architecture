import SwiftUI
import app_di_swift
import lib_basic_swift
import Foundation

struct GlobalContentView: View {

    let appDiIos = SampleDiIos()
    @ObservedObject var myViewModel:GlobalViewModel

    init() {
        self.myViewModel = GlobalViewModel(di: appDiIos.common)
    }

    var body: some View {
        VStack {
            //Text("updateCount: \(myViewModel.myState.updateCount)")
            appDiIos.solutionBonusIos.renderBonusesAndRefillButton()
            appDiIos.solutionBonusIos.renderBonusToggle()
        }
    }

}
