package com.kmpdroidcon.todokmp.di

import com.kmpdroidcon.core.di.TodoCoreModule
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.TodoListViewModelImpl

object TodoSharedModule {
    fun providesTodoListViewModel(): TodoListViewModel {
        return TodoListViewModelImpl(
            addTodoUseCase = TodoCoreModule.providesAddTodoUseCase(),
            fetchTodosUseCase = TodoCoreModule.providesFetchTodosUseCase()
        )
    }
}