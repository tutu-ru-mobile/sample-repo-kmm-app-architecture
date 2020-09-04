import SwiftUI
import Foundation
import ios_kotlin_pod
import lib_basic_swift
import solution_search_start_api_swift

public struct SolutionSearchStartIosImpl: SolutionSearchStartIosApi {

    public init() {

    }

    public func renderSearchStart() -> some View {
        Text("Выполняем поиск билетов")
    }
}
