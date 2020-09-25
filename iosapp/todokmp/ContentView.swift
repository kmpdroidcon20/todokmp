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

class TodoListViewModelWrapper: ObservableObject {
    var viewModel : TodoListViewModel
    // Store state
    @Published var todos: [TodoSwiftUiItem] = []

    init(viewModel : TodoListViewModel) {
        self.viewModel = viewModel
        self.viewModel.todoStream.subscribe(isThreadLocal: false) { [weak self] (todos) in
            self?.todos = (todos as! [TodoUiItem]).map { (item : TodoUiItem) -> TodoSwiftUiItem in
                TodoSwiftUiItem(timestamp: item.timestamp, content: item.content)
            }
        }
    }

    func createTodo(content: String) {
        self.viewModel.createTodo(content: content)
    }
}

struct ContentView: View {
    // TODO understand DI here
    @ObservedObject private var viewModel : TodoListViewModelWrapper = TodoListViewModelWrapper(viewModel: TodoListViewModelImpl())
    @State private var todoContent: String = "TODO content"
    var body: some View {
        VStack{
            HStack{
                TextField("TODO content", text: $todoContent)
                Button("Create", action: {
                    self.viewModel.createTodo(content: self.todoContent)
                })
            }
            List(viewModel.todos) { item in
                VStack {
                    Text(item.timestamp)
                    Text(item.content)
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
