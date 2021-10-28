package com.kmpdroidcon.todokmp.integration

import com.badoo.reaktive.scheduler.overrideSchedulers
import com.badoo.reaktive.scheduler.trampolineScheduler
import com.badoo.reaktive.test.observable.assertValue
import com.badoo.reaktive.test.observable.test
import com.careem.mockingbird.test.capture
import com.careem.mockingbird.test.slot
import com.careem.mockingbird.test.verify
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.Database
import com.kmpdroidcon.todokmp.PlatformIntegrationTest
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource
import com.kmpdroidcon.todokmp.di.DIGraphImpl
import com.kmpdroidcon.todokmp.mock.InMemoryTodoDataSourceSpy
import com.kmpdroidcon.todokmp.mock.PersistedTodoDataSourceSpy
import com.kmpdroidcon.todokmp.sqldelight.DatabaseInitializer
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import com.kmpdroidcon.util.isFrozen
import kotlinx.atomicfu.atomic
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class TodokmpIntegrationTest : PlatformIntegrationTest() {

    private val diGraph = TestDIGraph()
    private val todoListViewModel = diGraph.build(testInjector().platformDependency())

    @BeforeTest
    fun setUp() {
        overrideSchedulers(
            main = { trampolineScheduler },
            io = { trampolineScheduler }
        )
    }

    @Test
    fun testTodoInsertion() {
        todoListViewModel.initialize()
        assertFalse(todoListViewModel.isFrozen)

        val testObserver = todoListViewModel.todoStream.test()
        testObserver.assertValue(emptyList()) // No items on init

        todoListViewModel.createTodo(TODO_CONTENT_1)

        verifyTodoMemoryInsert(TODO_CONTENT_1)
        verifyTodoPersistenceInsert(TODO_CONTENT_1)

        assertEquals(2, testObserver.values.size) // New item emitted with right content
        assertEquals(TODO_CONTENT_1, testObserver.values.last().first().content)
    }

    private fun verifyTodoMemoryInsert(todoContent: String) {
        val inMemoryTodoItemSlot = slot<TodoItem>()
        diGraph.memoryTodoDataSource.verify(
            methodName = InMemoryTodoDataSourceSpy.Method.addTodo,
            arguments = mapOf(
                InMemoryTodoDataSourceSpy.Arg.todoItem to capture(inMemoryTodoItemSlot)
            )
        )
        assertNotNull(inMemoryTodoItemSlot.captured)
        val inMemoryCapturedTodo = inMemoryTodoItemSlot.captured!!
        assertEquals(todoContent, inMemoryCapturedTodo.todo)
    }

    private fun verifyTodoPersistenceInsert(todoContent: String) {
        val persistedTodoItemSlot = slot<TodoItem>()
        diGraph.persistedTodoDataSource.verify(
            methodName = PersistedTodoDataSourceSpy.Method.addTodo,
            arguments = mapOf(
                PersistedTodoDataSourceSpy.Arg.todoItem to capture(persistedTodoItemSlot)
            )
        )
        assertNotNull(persistedTodoItemSlot.captured)
        val capturedTodo = persistedTodoItemSlot.captured!!
        assertEquals(todoContent, capturedTodo.todo)
    }


    class TestDIGraph : DIGraphImpl() {
        private val _memoryTodoDataSource = atomic<InMemoryTodoDataSourceSpy?>(null)
        val memoryTodoDataSource: InMemoryTodoDataSourceSpy
            get() {
                return _memoryTodoDataSource.value!!
            }
        private val _persistedTodoDataSource = atomic<PersistedTodoDataSourceSpy?>(null)
        val persistedTodoDataSource: PersistedTodoDataSourceSpy
            get() {
                return _persistedTodoDataSource.value!!
            }

        private val _database = atomic<Database?>(null)
        val database: Database
            get() {
                return _database.value!!
            }

        override fun memoryDataSource(): InMemoryTodoDataSource =
            InMemoryTodoDataSourceSpy(super.memoryDataSource()).apply {
                _memoryTodoDataSource.value = this
            }

        // Uncomment following lines and comment lines above to reproduce immutability issue on iOS
//        override fun memoryDataSource(): InMemoryTodoDataSource =
//            InMemoryTodoDataSourceSpy(TodoInMemoryDataSourceModule.providesBrokenInMemoryTodoDataSource()).apply {
//                _memoryTodoDataSource.value = this
//            }

        override fun buildPersistedTodoDataSource(todoItemDao: TodoItemDao): PersistedTodoDataSource =
            PersistedTodoDataSourceSpy(super.buildPersistedTodoDataSource(todoItemDao)).apply {
                _persistedTodoDataSource.value = this
            }

        override fun database(databaseInitializer: DatabaseInitializer): Database =
            super.database(databaseInitializer).apply {
                _database.value = this
            }
    }

    @AfterTest
    fun tearDown() {
        diGraph.database.todoItemModelQueries.deleteAll()
    }

    companion object {
        const val TODO_CONTENT_1 = "TODO_CONTENT_1"
    }
}

