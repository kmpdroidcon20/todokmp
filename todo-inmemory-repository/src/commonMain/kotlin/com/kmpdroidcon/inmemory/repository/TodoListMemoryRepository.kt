package com.kmpdroidcon.inmemory.repository

import co.touchlab.stately.collections.IsoMutableList
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.util.freeze

class TodoListMemoryRepository : TodoListRepository {
    private val cache = IsoMutableList<TodoItem>()

    init {
        freeze()
    }

    override fun addTodo(todoItem: TodoItem) {
        cache.add(todoItem)
    }

    override fun getAll(): List<TodoItem> = cache

    override fun count(): Int = cache.size
}