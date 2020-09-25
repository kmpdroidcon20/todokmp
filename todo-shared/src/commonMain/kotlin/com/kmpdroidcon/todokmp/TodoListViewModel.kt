package com.kmpdroidcon.todokmp

import com.badoo.reaktive.observable.ObservableWrapper
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem

interface TodoListViewModel {
    fun initialize()
    fun destroy()
    val todoStream: ObservableWrapper<List<TodoUiItem>>
    fun createTodo(content: String)
}