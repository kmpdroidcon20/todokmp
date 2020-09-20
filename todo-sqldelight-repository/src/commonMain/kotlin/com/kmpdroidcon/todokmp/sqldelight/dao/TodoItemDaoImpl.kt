package com.kmpdroidcon.todokmp.sqldelight.dao

import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.Database
import com.kmpdroidcon.todokmp.TodoItemModel

class TodoItemDaoImpl(database: Database) : TodoItemDao {
    private val db = database.todoItemModelQueries

    override fun insert(todoItem: TodoItem) {
        db.insertItem(todoItem.timestamp, todoItem.todo)
    }

    override fun selectAll(): List<TodoItemModel> =
        db.selectAll().executeAsList()

    override fun deleteAll() {
        db.deleteAll()
    }

    override fun count(): Int =
        db.count().executeAsOne().toInt()
}