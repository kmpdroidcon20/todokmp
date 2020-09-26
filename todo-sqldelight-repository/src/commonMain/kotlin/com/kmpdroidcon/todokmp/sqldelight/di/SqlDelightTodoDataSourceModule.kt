package com.kmpdroidcon.todokmp.sqldelight.di

import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import com.kmpdroidcon.todokmp.sqldelight.repository.PersistedTodoDataSourceImpl

object SqlDelightTodoDataSourceModule {
    fun providesPersistedTodoDataSource(todoItemDao: TodoItemDao): PersistedTodoDataSource{
        return PersistedTodoDataSourceImpl(todoItemDao)
    }
}