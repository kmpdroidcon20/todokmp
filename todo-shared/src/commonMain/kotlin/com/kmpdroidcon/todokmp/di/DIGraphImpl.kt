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

open class DIGraphImpl: DIGraph {

    override fun memoryDataSource() = TodoInMemoryDataSourceModule.providesInMemoryTodoDataSource()
    override fun todoItemDao(database: Database) =
        SqlDelightTodoDataSourceModule.provideTodoItemDao(database)

    override fun buildPersistedTodoDataSource(todoItemDao: TodoItemDao) =
        SqlDelightTodoDataSourceModule.providesPersistedTodoDataSource(todoItemDao)

    override fun timeUtil() = TodoSharedModule.providesTimeUtil()
    override fun todoListRepository(
        memoryDataSource: InMemoryTodoDataSource,
        diskDataSource: PersistedTodoDataSource
    ) = TodoDataModule.providesTodoListRepository(
        memoryDataSource = memoryDataSource,
        diskDataSource = diskDataSource
    )

    override fun addTodoUseCase(
        todoListRepository: TodoListRepository,
        timeUtil: TimeUtil
    ) = TodoCoreModule.providesAddTodoUseCase(
        todoListRepository = todoListRepository,
        timeUtil = timeUtil
    )

    override fun fetchTodosUseCase(
        todoListRepository: TodoListRepository
    ) = TodoCoreModule.providesFetchTodosUseCase(
        todoListRepository = todoListRepository
    )

    override fun todoListViewModel(
        addTodoUseCase: AddTodoUseCase,
        fetchTodosUseCase: FetchTodosUseCase
    ) = TodoSharedModule.providesTodoListViewModel(
        addTodoUseCase = addTodoUseCase,
        fetchTodosUseCase = fetchTodosUseCase
    )

    override fun databaseInitializer(platformDependencyProvider: PlatformDependencyProvider): DatabaseInitializer = TodoSharedModule.providesDatabaseInitializer(platformDependencyProvider)

    override fun database(
        databaseInitializer: DatabaseInitializer
    )= databaseInitializer.getDatabase()

    override fun platformDependencyProvider(platformDependency: PlatformDependency) = TodoSharedModule.providesPlatformDependencyProvider(platformDependency)


    override fun build(platformDependency: PlatformDependency): TodoListViewModel {
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


interface DIGraph {
    fun memoryDataSource(): InMemoryTodoDataSource
    fun todoItemDao(database: Database): TodoItemDao
    fun buildPersistedTodoDataSource(todoItemDao: TodoItemDao): PersistedTodoDataSource
    fun timeUtil(): TimeUtil
    fun todoListRepository(memoryDataSource: InMemoryTodoDataSource, diskDataSource: PersistedTodoDataSource): TodoListRepository
    fun addTodoUseCase(todoListRepository: TodoListRepository, timeUtil: TimeUtil): AddTodoUseCase
    fun fetchTodosUseCase(todoListRepository: TodoListRepository): FetchTodosUseCase
    fun todoListViewModel(addTodoUseCase: AddTodoUseCase, fetchTodosUseCase: FetchTodosUseCase): TodoListViewModel
    fun databaseInitializer(platformDependencyProvider: PlatformDependencyProvider): DatabaseInitializer
    fun database(databaseInitializer: DatabaseInitializer): Database
    fun platformDependencyProvider(platformDependency: PlatformDependency): PlatformDependencyProvider
    fun build(platformDependency: PlatformDependency): TodoListViewModel
}