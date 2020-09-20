package com.kmpdroidcon.todokmp.sqldelight.repository

import com.kmpdroidcon.core.repository.RepositoryProvider
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao

class RepositorySqlDelightProvider(private val todoItemDao: TodoItemDao) : RepositoryProvider {
    override fun todoListRepository(): TodoListRepository =
        TodoListSqldelightRepository(todoItemDao)
}