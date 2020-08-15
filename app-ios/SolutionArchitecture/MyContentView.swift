import SwiftUI
import app_di_swift
import app_di
import Foundation

struct MyContentView: View {
    @ObservedObject var myViewModel = MyViewModel()

    var body: some View {
        TabView {
            MyListView(myViewModel: myViewModel, param1: "val1")
                    .tabItem {
                        VStack {
                            Image(systemName: "1.circle")
                            Text("Tab1")
                        }
                    }
            MyListView(myViewModel: myViewModel, param1: "val2")
                    .tabItem {
                        VStack {
                            Image(systemName: "2.circle")
                            Text("Tab2")
                        }
                    }
        }
    }
}

struct MyListView: View {
    @ObservedObject var myViewModel: MyViewModel
    var param1: String//Можно параметризировать View

    var body: some View {
        NavigationView {
            List(myViewModel.myState.list, id: \.id) { item in
                MyItemView(item: item, solutionA: SolutionAImpl()) {
                    self.myViewModel.doAction(action: MyAction.Click(item: item))
                }
            }.navigationBarTitle("nav bar title")
        }
    }
}

public struct MyItemView<S1:SolutionAApi>: View {
    var item: MyItem
    var clickAction: () -> Void
    var solutionA:S1

    public init(item: MyItem, solutionA: S1, clickAction: @escaping () -> ()) {
        self.item = item
        self.clickAction = clickAction
        self.solutionA = solutionA
    }

    public var body: some View {
        HStack {
//            Image("ic_bike").resizable()
//                    .renderingMode(.template)
//                    .foregroundColor(false ? .orange : .green)
//                    .frame(width: 32.0, height: 32.0)

            Spacer().frame(width: 16)

            VStack(alignment: .leading) {
                Text(item.text).font(.headline)
                HStack {
                    Text("Free:").font(.subheadline).frame(width: 80, alignment: .leading)
                    Text("\(item.counter1)").font(.subheadline)
                }
                HStack {
                    Text("Slots:").font(.subheadline).frame(width: 80, alignment: .leading)
                    Text("\(item.counter2)").font(.subheadline)
                }
                solutionA.render1()
                solutionA.render2()
            }
            Button(action: { self.clickAction() }) {
                Text("Click")
            }
        }
    }
}

public protocol SolutionAApi {
    associatedtype V1:View
    associatedtype V2:View
    func render1() -> V1
    func render2() -> V2
}

struct SolutionAImpl : SolutionAApi {

    func render1() -> some View {
        HStack{
            Text("render1")
            Text("render1")
        }
    }

    func render2() -> some View {
        Text("render2")
    }
}

class MyViewModel: ObservableObject {
    @Published var myState: MyState
    private let kotlinModel: MyKotlinModel

    init() {
        self.kotlinModel = MyKotlinModel()
        myState = kotlinModel.getLastState()
        kotlinModel.addListener(listener: {state in
            self.myState = state
        })
    }

    func doAction(action: MyAction) {
        kotlinModel.doAction(action: action)
    }

}

//func buildUi<Content>(@ViewBuilder content: @escaping () -> Content)
//        where Content : View
//{
//    func f1() {
//        let content2: () -> Content = content
//    }
//}
//
//func useBuilder() {
//    buildUi {
//        Text("txt")
//    }
//}
