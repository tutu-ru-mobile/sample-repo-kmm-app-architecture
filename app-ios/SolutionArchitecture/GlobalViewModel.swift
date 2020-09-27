import app_di_swift
import ios_kotlin_pod
import lib_basic_swift
import Foundation

public class GlobalViewModel: ObservableObject {
    @Published public var myState: App_diGlobalState

    public init(di:App_diAppDi) {
        myState = di.getLastState()
        di.addListener(listener: {state in
            self.myState = state
        })
    }

}
