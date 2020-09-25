package com.kmpdroidcon.core.di

import com.kmpdroidcon.core.usecase.AddTodoUseCase
import com.kmpdroidcon.core.usecase.AddTodoUseCaseImpl
import com.kmpdroidcon.core.usecase.FetchTodosUseCase
import com.kmpdroidcon.core.usecase.FetchTodosUseCaseImpl

object TodoCoreModule {
    fun providesFetchTodosUseCase(): FetchTodosUseCase {
        return FetchTodosUseCaseImpl()
    }

    fun providesAddTodoUseCase(): AddTodoUseCase {
        return AddTodoUseCaseImpl()
    }
}