//
//  ContentView.swift
//  SolutionArchitecture
//
//  Created by Dim on 12.08.2020.
//  Copyright © 2020 sample. All rights reserved.
//

import SwiftUI
//import solution_ios
import app_di_swift
import app_di

struct ContentView: View {
    var body: some View {
        VStack() {
            Text("Hello, World!")
            Text(UseKotlin2().getStr())
            StationView(station: "station1") {}
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

func todo2() {
    AppDi().auth.login
}

