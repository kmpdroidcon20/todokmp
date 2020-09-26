package com.kmpdroidcon.core.di

import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.core.usecase.AddTodoUseCase
import com.kmpdroidcon.core.usecase.AddTodoUseCaseImpl
import com.kmpdroidcon.core.usecase.FetchTodosUseCase
import com.kmpdroidcon.core.usecase.FetchTodosUseCaseImpl
import com.kmpdroidcon.util.time.TimeUtil

object TodoCoreModule {
    fun providesFetchTodosUseCase(todoListRepository: TodoListRepository): FetchTodosUseCase {
        return FetchTodosUseCaseImpl(todoListRepository)
    }

    fun providesAddTodoUseCase(
        todoListRepository: TodoListRepository,
        timeUtil: TimeUtil
    ): AddTodoUseCase {
        return AddTodoUseCaseImpl(todoListRepository, timeUtil)
    }
}