package com.kmpdroidcon.todokmp.sqldelight.dao

import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.TodoItemModel

interface TodoItemDao {
    fun insert(todoItem: TodoItem)
    fun selectAll(): List<TodoItemModel>
    fun deleteAll()
    fun count(): Int
}