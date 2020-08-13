//
//  ContentView.swift
//  SolutionArchitecture
//
//  Created by Dim on 12.08.2020.
//  Copyright © 2020 sample. All rights reserved.
//

import SwiftUI
import solution_ios

struct ContentView: View {
    var body: some View {
        VStack() {
            Text("Hello, World!")
            Text(UseKotlin().getStr())
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

