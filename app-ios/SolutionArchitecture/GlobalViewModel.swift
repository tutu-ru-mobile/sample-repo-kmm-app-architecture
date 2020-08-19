import app_di_swift
import app_di
import lib_basic_swift
import Foundation

public class GlobalViewModel: ObservableObject {
    @Published public var myState: AppDi.GlobalState

    public init(di:AppDi) {
        myState = di.getLastState()
        di.addListener(listener: {state in
            self.myState = state
        })
    }

}
