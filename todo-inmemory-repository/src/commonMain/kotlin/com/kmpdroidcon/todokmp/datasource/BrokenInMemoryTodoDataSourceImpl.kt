package com.kmpdroidcon.todokmp.datasource

import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.util.freeze

internal class BrokenInMemoryTodoDataSourceImpl
    : InMemoryTodoDataSource {
    private val cache: MutableList<TodoItem> = mutableListOf()
    init { freeze() }

    override fun addTodo(todoItem: TodoItem) {
        cache.add(todoItem)
    }

    override fun init(todos: List<TodoItem>) {
        cache.clear()
        cache.addAll(todos)
    }

    override fun getAll(): List<TodoItem> = cache
}