package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.test.completable.test
import com.careem.mockingbird.test.every
import com.careem.mockingbird.test.verify
import com.kmpdroidcon.core.mock.TimeUtilMock
import com.kmpdroidcon.core.mock.TimeUtilMock.Method.getTimeInMillis
import com.kmpdroidcon.core.mock.TodoListRepositoryMock
import com.kmpdroidcon.core.mock.TodoListRepositoryMock.Method.addTodo
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.util.threadedTest
import kotlinx.atomicfu.atomic
import kotlin.test.BeforeTest
import kotlin.test.Test

class AddTodoUseCaseImplTest {
    private val todoListRepositoryMock = TodoListRepositoryMock()
    private val timeUtilMock = TimeUtilMock()

    private val addTodoUseCaseImpl =
        AddTodoUseCaseImpl(todoListRepositoryMock, timeUtilMock)

    @BeforeTest
    fun setup() {
        timeUtilMock.every(methodName = getTimeInMillis) { TIMESTAMP }
    }

    @Test
    fun testExecute() {
        val runs = atomic(0)
        threadedTest {
            val todo = "Play Fifa"
            val testItem = TodoItem(TIMESTAMP, todo)
            addTodoUseCaseImpl.execute(todo).test()

            timeUtilMock.verify(exactly = runs.addAndGet(1), methodName = getTimeInMillis)
            todoListRepositoryMock.verify(
                exactly = runs.value,
                methodName = addTodo,
                arguments = mapOf(TodoListRepositoryMock.Arg.todoItem to testItem)
            )
        }
    }

    companion object {
        private const val TIMESTAMP = 12345L
    }
}