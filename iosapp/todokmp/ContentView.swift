//
//  ContentView.swift
//  todokmp
//
//  Created by Marco Signoretto (old) on 21/09/2020.
//  Copyright © 2020 kmpdroidcon20. All rights reserved.
//

import SwiftUI
import TodoCommon

struct ContentView: View {
    let repo : TodoListRepository? = nil // TODO remove this
    var body: some View {
        Text("Hello, World!")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
