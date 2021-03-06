package com.kmpdroidcon.inmemory.repository

import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.datasource.InMemoryTodoDataSourceImpl
import com.kmpdroidcon.util.threadedTest
import kotlinx.atomicfu.atomic
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InMemoryTodoDataSourceTest {
    private val todoListMemoryRepository = InMemoryTodoDataSourceImpl()

    @Test
    fun testAddTodo() {
        val count = 5
        val todoList = buildTestTodo(count)
        todoList.forEach { todoListMemoryRepository.addTodo(it) }

        val runs = atomic(0)
        threadedTest {
            val todo = buildTestTodo(1, runs.value.toString()).first()
            todoListMemoryRepository.addTodo(todo)
            assertTrue { todoListMemoryRepository.getAll().contains(todo) }
        }
    }

    @Test
    fun testGetAll() {
        val todoList = buildTestTodo(5)
        todoList.forEach { todoListMemoryRepository.addTodo(it) }

        threadedTest {
            assertEquals(todoList, todoListMemoryRepository.getAll())
        }
    }

    companion object {
        private fun buildTestTodo(count: Int, prefix: String = ""): List<TodoItem> {
            return (0 until count).mapIndexed { index, _ ->
                TodoItem(
                    timestamp = 12345L + index,
                    todo = "$prefix TODO$index"
                )
            }
        }
    }
}