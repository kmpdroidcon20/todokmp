package com.kmpdroidcon.todokmp.data.dependency

import com.kmpdroidcon.core.model.TodoItem

interface PersistedTodoDataSource {
    fun addTodo(todoItem: TodoItem)
    fun getAll(): List<TodoItem>
}