package com.kmpdroidcon.todokmp.sqldelight.repository

import com.careem.mockingbird.test.*
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.TodoItemModel
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SqldelightPersistedTodoDataSourceTest {
    private val todoItemDaoMock = TodoItemDaoMock()

    private lateinit var persistedTodoDataSourceImpl: PersistedTodoDataSourceImpl

    @BeforeTest
    fun setup() {
        persistedTodoDataSourceImpl = PersistedTodoDataSourceImpl(
            todoItemDaoMock
        )
    }

    @Test
    fun testInsertFirstItem() {
        val todoItem = TodoItem(TEST_TIMESTAMP, TEST_TODO)

        todoItemDaoMock.every(
            methodName = TodoItemDaoMock.Method.count
        ) { 0 }
        persistedTodoDataSourceImpl.addTodo(todoItem)

        todoItemDaoMock.verify(
            methodName = TodoItemDaoMock.Method.insert,
            arguments = mapOf(TodoItemDaoMock.Arg.todoItem to todoItem)
        )

        todoItemDaoMock.verify(
            methodName = TodoItemDaoMock.Method.count
        )
    }

    @Test
    fun testInsertItem() {
        val todoItem = TodoItem(TEST_TIMESTAMP, TEST_TODO)

        todoItemDaoMock.every(
            methodName = TodoItemDaoMock.Method.count
        ) { 5 }
        persistedTodoDataSourceImpl.addTodo(todoItem)

        todoItemDaoMock.verify(
            methodName = TodoItemDaoMock.Method.insert,
            arguments = mapOf(TodoItemDaoMock.Arg.todoItem to todoItem)
        )
        todoItemDaoMock.verify(
            methodName = TodoItemDaoMock.Method.count
        )
    }

    @Test
    fun testGetAll() {
        val todoItem1 = TodoItemModel(TEST_TIMESTAMP, TEST_TODO)
        val todoItem2 = TodoItemModel(TEST_TIMESTAMP_2, TEST_TODO)
        val todoItemModels = listOf(todoItem1, todoItem2)
        val todoItemExpectedResult = listOf(
            TodoItem(TEST_TIMESTAMP, TEST_TODO),
            TodoItem(TEST_TIMESTAMP_2, TEST_TODO)
        )
        todoItemDaoMock.every(
            methodName = TodoItemDaoMock.Method.selectAll
        ) { todoItemModels }

        val result = persistedTodoDataSourceImpl.getAll()
        assertEquals(todoItemExpectedResult, result)
    }

    companion object {
        private const val TEST_TIMESTAMP = 12345L
        private const val TEST_TIMESTAMP_2 = 54321L
        private const val TEST_TODO = "Make Coffee"
    }

    internal class TodoItemDaoMock : TodoItemDao, Mock {
        object Method {
            const val insert = "insert"
            const val selectAll = "selectAll"
            const val deleteAll = "deleteAll"
            const val count = "count"
        }

        object Arg {
            const val todoItem = "todoItem"
        }

        override fun insert(todoItem: TodoItem) =
            mockUnit(
                methodName = Method.insert,
                arguments = mapOf(Arg.todoItem to todoItem)
            )

        override fun selectAll(): List<TodoItemModel> = mock(
            methodName = Method.selectAll
        )

        override fun deleteAll() = mockUnit(
            methodName = Method.deleteAll
        )

        override fun count(): Int = mock(
            methodName = Method.count
        )
    }
}