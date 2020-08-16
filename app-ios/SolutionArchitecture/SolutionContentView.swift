import SwiftUI
import app_di
import app_di_swift
import Foundation

struct SolutionContentView: View {
    @ObservedObject var myViewModel = MyViewModel()

    var body: some View {
        AppDiIos().tabs.renderScaffold()
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

