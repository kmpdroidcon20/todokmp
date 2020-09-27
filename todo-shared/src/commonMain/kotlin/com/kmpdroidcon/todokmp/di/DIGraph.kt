package com.kmpdroidcon.todokmp.di

import com.kmpdroidcon.core.di.TodoCoreModule
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.core.usecase.AddTodoUseCase
import com.kmpdroidcon.core.usecase.FetchTodosUseCase
import com.kmpdroidcon.todokmp.Database
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource
import com.kmpdroidcon.todokmp.data.di.TodoDataModule
import com.kmpdroidcon.todokmp.datasource.di.TodoInMemoryDataSourceModule
import com.kmpdroidcon.todokmp.dependency.PlatformDependency
import com.kmpdroidcon.todokmp.dependency.PlatformDependencyProvider
import com.kmpdroidcon.todokmp.sqldelight.DatabaseInitializer
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import com.kmpdroidcon.todokmp.sqldelight.di.SqlDelightTodoDataSourceModule
import com.kmpdroidcon.util.time.TimeUtil

open class DIGraph {

    open fun memoryDataSource() = TodoInMemoryDataSourceModule.providesInMemoryTodoDataSource()
    open fun todoItemDao(database: Database) =
        SqlDelightTodoDataSourceModule.provideTodoItemDao(database)

    open fun buildPersistedTodoDataSource(todoItemDao: TodoItemDao) =
        SqlDelightTodoDataSourceModule.providesPersistedTodoDataSource(todoItemDao)

    open fun timeUtil() = TodoSharedModule.providesTimeUtil()
    open fun todoListRepository(
        memoryDataSource: InMemoryTodoDataSource,
        diskDataSource: PersistedTodoDataSource
    ) = TodoDataModule.providesTodoListRepository(
        memoryDataSource = memoryDataSource,
        diskDataSource = diskDataSource
    )

    open fun addTodoUseCase(
        todoListRepository: TodoListRepository,
        timeUtil: TimeUtil
    ) = TodoCoreModule.providesAddTodoUseCase(
        todoListRepository = todoListRepository,
        timeUtil = timeUtil
    )

    open fun fetchTodosUseCase(
        todoListRepository: TodoListRepository
    ) = TodoCoreModule.providesFetchTodosUseCase(
        todoListRepository = todoListRepository
    )

    open fun todoListViewModel(
        addTodoUseCase: AddTodoUseCase,
        fetchTodosUseCase: FetchTodosUseCase
    ) = TodoSharedModule.providesTodoListViewModel(
        addTodoUseCase = addTodoUseCase,
        fetchTodosUseCase = fetchTodosUseCase
    )

    open fun databaseInitializer(platformDependencyProvider: PlatformDependencyProvider): DatabaseInitializer = TodoSharedModule.providesDatabaseInitializer(platformDependencyProvider)

    open fun database(
        databaseInitializer: DatabaseInitializer
    )= databaseInitializer.getDatabase()

    open fun platformDependencyProvider(platformDependency: PlatformDependency) = TodoSharedModule.providesPlatformDependencyProvider(platformDependency)


    fun build(platformDependency: PlatformDependency): TodoListViewModel {
        val platformDependencyProvider = platformDependencyProvider(platformDependency)
        val databaseInitializer = databaseInitializer(platformDependencyProvider)
        val database = database(databaseInitializer)
        val todoItemDao = todoItemDao(database)
        val diskDataSource = buildPersistedTodoDataSource(todoItemDao)
        val memoryDataSource = memoryDataSource()
        val timeUtil = timeUtil()
        val todoListRepository = todoListRepository(memoryDataSource, diskDataSource)
        val addTodoUseCase = addTodoUseCase(todoListRepository, timeUtil)
        val fetchTodosUseCase = fetchTodosUseCase(todoListRepository)
        return todoListViewModel(addTodoUseCase, fetchTodosUseCase)
    }
}