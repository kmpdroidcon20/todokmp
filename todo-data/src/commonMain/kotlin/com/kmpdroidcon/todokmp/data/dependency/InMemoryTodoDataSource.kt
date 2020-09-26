package com.kmpdroidcon.todokmp.data.dependency

import com.kmpdroidcon.core.model.TodoItem

interface InMemoryTodoDataSource {
    fun addTodo(todoItem: TodoItem)
    fun getAll(): List<TodoItem>
    fun init(todos: List<TodoItem>)
}