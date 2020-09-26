package com.kmpdroidcon.todokmp.di

import com.kmpdroidcon.core.di.TodoCoreModule
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.TodoListViewModelImpl
import com.kmpdroidcon.todokmp.dependency.PlatformDependency
import com.kmpdroidcon.todokmp.dependency.PlatformDependencyProvider
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDaoImpl
import com.kmpdroidcon.todokmp.sqldelight.repository.RepositorySqlDelightProvider
import com.kmpdroidcon.util.time.TimeUtilImpl
import kotlinx.atomicfu.atomic

object TodoSharedModule {

    private val platformDependencyProvider = atomic<PlatformDependencyProvider?>(null)

    private fun providePlatformDependencyProvider(platformDependency: PlatformDependency): PlatformDependencyProvider {
        // FIXME this will lead to instantiation of another provider that won't be used
        platformDependencyProvider.compareAndSet(
            null,
            PlatformDependencyProvider(platformDependency)
        )
        return platformDependencyProvider.value!!
    }

    private fun provideTodoItemDao(platformDependency: PlatformDependency): TodoItemDao {
        val platformDependencyProvider = providePlatformDependencyProvider(platformDependency)
        val database = platformDependencyProvider.provideDatabaseInitializer().getDatabase()
        return TodoItemDaoImpl(database)
    }

    /**
     * TODO this can accept the specific type of provider to repository
     *
     * For simplicity just using Sqldelight repository
     */
    private fun providesTodoListRepository(platformDependency: PlatformDependency): TodoListRepository {
        val todoItemDao = provideTodoItemDao(platformDependency)
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
    fun providesTodoListViewModel(platformDependency: PlatformDependency): TodoListViewModel {
        val todoListRepository = providesTodoListRepository(platformDependency)
        val timeUtil = TimeUtilImpl()
        return TodoListViewModelImpl(
            addTodoUseCase = TodoCoreModule.providesAddTodoUseCase(todoListRepository, timeUtil),
            fetchTodosUseCase = TodoCoreModule.providesFetchTodosUseCase(todoListRepository)
        )
    }
}