package com.kmpdroidcon.todokmp.integration

import com.badoo.reaktive.scheduler.overrideSchedulers
import com.badoo.reaktive.scheduler.trampolineScheduler
import com.badoo.reaktive.test.observable.assertValue
import com.badoo.reaktive.test.observable.test
import com.careem.mockingbird.test.Slot
import com.careem.mockingbird.test.capture
import com.careem.mockingbird.test.verify
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.Database
import com.kmpdroidcon.todokmp.PlatformIntegrationTest
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource
import com.kmpdroidcon.todokmp.datasource.di.TodoInMemoryDataSourceModule
import com.kmpdroidcon.todokmp.di.DIGraph
import com.kmpdroidcon.todokmp.mock.InMemoryTodoDataSourceSpy
import com.kmpdroidcon.todokmp.mock.PersistedTodoDataSourceSpy
import com.kmpdroidcon.todokmp.sqldelight.DatabaseInitializer
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import com.kmpdroidcon.todokmp.sqldelight.di.SqlDelightTodoDataSourceModule
import com.kmpdroidcon.util.isFrozen
import kotlinx.atomicfu.atomic
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class TodokmpIntegrationTest : PlatformIntegrationTest() {

    val diGraph = TestDIGraph()
    val todoListViewModel = diGraph.build(testInjector().platformDependency())

    @BeforeTest
    fun setUp() {
        overrideSchedulers(
            main = { trampolineScheduler },
            io = { trampolineScheduler }
        )

        // Clean Up DB, better approach is to provide in memory impl in case of testing
        diGraph.database.todoItemModelQueries.deleteAll()
    }

    @Test
    fun testTodoInsertion() {
        todoListViewModel.initialize()
        assertFalse(todoListViewModel.isFrozen)

        val testObserver = todoListViewModel.todoStream.test()

        // No items on init
        testObserver.assertValue(emptyList())

        todoListViewModel.createTodo(TODO_CONTENT_1)

        verifyTodoMemoryInsert(TODO_CONTENT_1)
        verifyTodoPersistenceInsert(TODO_CONTENT_1)

        // New item emitted with right content
        assertEquals(2, testObserver.values.size)
        assertEquals(TODO_CONTENT_1, testObserver.values.last().first().content)
    }

    private fun verifyTodoMemoryInsert(todoContent: String) {
        // Verify memory insertion
        val inMemoryTodoItemSlot = Slot<TodoItem>()
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
        // Verify persistence insertion
        val persistedTodoItemSlot = Slot<TodoItem>()
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


    class TestDIGraph : DIGraph() {
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

        override fun memoryDataSource(): InMemoryTodoDataSource {
            val value =
                InMemoryTodoDataSourceSpy(TodoInMemoryDataSourceModule.providesInMemoryTodoDataSource())
            _memoryTodoDataSource.value = value
            return value
        }

        override fun buildPersistedTodoDataSource(todoItemDao: TodoItemDao): PersistedTodoDataSource {
            val value = PersistedTodoDataSourceSpy(
                SqlDelightTodoDataSourceModule.providesPersistedTodoDataSource(todoItemDao)
            )
            _persistedTodoDataSource.value = value
            return value
        }

        override fun database(databaseInitializer: DatabaseInitializer): Database {
            val db = super.database(databaseInitializer)
            _database.value = db
            return db
        }
    }

    companion object {
        const val TODO_CONTENT_1 = "TODO_CONTENT_1"
    }
}

