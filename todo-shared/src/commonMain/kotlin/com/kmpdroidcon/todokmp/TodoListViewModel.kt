package com.kmpdroidcon.todokmp

import com.badoo.reaktive.observable.ObservableWrapper
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem

interface TodoListViewModel {
    val todoStream: ObservableWrapper<List<TodoUiItem>>
    fun createTodo(content: String)
}