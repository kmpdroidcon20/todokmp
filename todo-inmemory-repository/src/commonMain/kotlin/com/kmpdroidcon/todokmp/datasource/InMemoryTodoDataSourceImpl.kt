package com.kmpdroidcon.todokmp.datasource

import co.touchlab.stately.collections.IsoMutableList
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.util.freeze

internal class InMemoryTodoDataSourceImpl : InMemoryTodoDataSource {
    private val cache = IsoMutableList<TodoItem>()

    init {
        freeze()
    }

    override fun addTodo(todoItem: TodoItem) {
        cache.add(todoItem)
    }

    override fun init(todos: List<TodoItem>) {
        cache.access {
            it.clear()
            it.addAll(todos)
        }
    }

    override fun getAll(): List<TodoItem> = cache
}