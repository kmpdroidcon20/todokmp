package com.kmpdroidcon.todokmp.sqldelight.repository

import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import kotlinx.atomicfu.atomic

class TodoListSqldelightRepository(private val todoItemDao: TodoItemDao) : TodoListRepository {
    private var todoCount: Int
        set(value) {
            todoCountRef.value = value
        }
        get() = todoCountRef.value

    private val todoCountRef = atomic(-1)

    override fun addTodo(todoItem: TodoItem) {
        refreshCount()
        todoItemDao.insert(todoItem)
        todoCount++
    }

    override fun getAll(): List<TodoItem> =
        todoItemDao.selectAll().map {
            TodoItem(it.timestamp, it.todo)
        }.also { todoCount = it.size }

    override fun count(): Int {
        refreshCount()
        return todoCount
    }

    private fun refreshCount() {
        if (todoCount < 0) {
            todoItemDao.count().apply { todoCount = this }
        }
    }
}