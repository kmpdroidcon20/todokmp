package com.kmpdroidcon.todokmp.di

import com.kmpdroidcon.core.usecase.AddTodoUseCase
import com.kmpdroidcon.core.usecase.FetchTodosUseCase
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.TodoListViewModelImpl
import com.kmpdroidcon.todokmp.dependency.PlatformDependency
import com.kmpdroidcon.todokmp.dependency.PlatformDependencyProvider
import com.kmpdroidcon.util.time.TimeUtil
import com.kmpdroidcon.util.time.TimeUtilImpl

object TodoSharedModule {

    fun providesPlatformDependencyProvider(platformDependency: PlatformDependency) =
        PlatformDependencyProvider(platformDependency)

    fun providesDatabaseInitializer(platformDependencyProvider: PlatformDependencyProvider) =
        platformDependencyProvider.provideDatabaseInitializer()

    fun providesTimeUtil(): TimeUtil {
        return TimeUtilImpl()
    }

    fun providesTodoListViewModel(
        addTodoUseCase: AddTodoUseCase,
        fetchTodosUseCase: FetchTodosUseCase
    ): TodoListViewModel {
        return TodoListViewModelImpl(
            addTodoUseCase = addTodoUseCase,
            fetchTodosUseCase = fetchTodosUseCase
        )
    }
}