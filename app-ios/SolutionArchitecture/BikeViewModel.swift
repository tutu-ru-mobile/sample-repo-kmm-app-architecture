import app_di_swift
import app_di
import lib_basic_swift
import Foundation

public class BikeViewModel: ObservableObject {
    @Published public var myState: MyState
    public let kotlinModel: MyKotlinModel

    public init() {
        self.kotlinModel = MyKotlinModel()
        myState = kotlinModel.getLastState()
        kotlinModel.addListener(listener: {state in
            self.myState = state
        })
    }

    public func doAction(action: MyAction) {
        kotlinModel.doAction(action: action)
    }

}
