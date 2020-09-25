//
//  ContentView.swift
//  todokmp
//
//  Created by Marco Signoretto (old) on 21/09/2020.
//  Copyright © 2020 kmpdroidcon20. All rights reserved.
//

import SwiftUI
import TodoCommon

struct TodoSwiftUiItem: Identifiable {
    var id = UUID()
    var timestamp: String
    var content: String
}

struct ContentView: View {
    // TODO make this one subscribe to shared ViewModel
    let modelData : [TodoSwiftUiItem] = [
        TodoUiItem(timestamp: "12/12/2020 12:40", content: "Todo 1"),
        TodoUiItem(timestamp: "12/12/2020 12:50", content: "Todo 2"),
        ].map{item in TodoSwiftUiItem(timestamp: item.timestamp, content: item.content)}

    var body: some View {
        List(modelData) { item in
            VStack {
                Text(item.timestamp)
                Text(item.content)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
