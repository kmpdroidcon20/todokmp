package com.kmpdroidcon.todokmp.data.di

import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.todokmp.data.TodoListRepositoryImpl
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource

object TodoDataModule {
    fun providesTodoListRepository(
        memoryDataSource: InMemoryTodoDataSource,
        diskDataSource: PersistedTodoDataSource
    ): TodoListRepository{
        return TodoListRepositoryImpl(
            memoryDataSource = memoryDataSource,
            diskDataSource = diskDataSource
        )
    }
}