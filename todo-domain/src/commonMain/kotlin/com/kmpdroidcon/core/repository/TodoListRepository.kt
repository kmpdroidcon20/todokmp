package com.kmpdroidcon.core.repository

import com.kmpdroidcon.core.model.TodoItem

interface TodoListRepository {
    fun addTodo(todoItem: TodoItem)
    fun getAll(): List<TodoItem>
    fun count(): Int
}