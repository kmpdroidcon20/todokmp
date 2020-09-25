package com.kmpdroidcon.todokmp.di

import com.kmpdroidcon.core.di.TodoCoreModule
import com.kmpdroidcon.core.repository.RepositoryProvider
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.TodoListViewModelImpl
import com.kmpdroidcon.todokmp.sqldelight.DatabaseInitializerImpl
import com.kmpdroidcon.todokmp.sqldelight.repository.RepositorySqlDelightProvider
import com.kmpdroidcon.util.time.TimeUtilImpl

object TodoSharedModule {
    /**
     * TODO this can accept the specific type of provider to repository
     *
     * For simplicity just using Sqldelight repository
     */
    private fun providesTodoListRepository(): TodoListRepository {
        val todoItemDao = DatabaseInitializerImpl().getDatabase()
        val repositoryProvider = RepositorySqlDelightProvider(todoItemDao)
        return repositoryProvider.todoListRepository()
    }

    /**
     * TODO this can accept a different TimeUtil implementation
     *
     * For simplicity instantiating everything here out of the box
     *
     * @return
     */
    fun providesTodoListViewModel(): TodoListViewModel {
        val todoListRepository = providesTodoListRepository()
        val timeUtil = TimeUtilImpl()
        return TodoListViewModelImpl(
            addTodoUseCase = TodoCoreModule.providesAddTodoUseCase(todoListRepository, timeUtil),
            fetchTodosUseCase = TodoCoreModule.providesFetchTodosUseCase(todoListRepository)
        )
    }
}