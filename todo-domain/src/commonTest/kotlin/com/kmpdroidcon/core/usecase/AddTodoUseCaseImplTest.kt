package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.test.completable.test
import com.careem.mockingbird.test.every
import com.careem.mockingbird.test.verify
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepositoryMock
import com.kmpdroidcon.util.threadedTest
import com.kmpdroidcon.util.time.TimeUtilMock
import kotlinx.atomicfu.atomic
import kotlin.test.BeforeTest
import kotlin.test.Test

class AddTodoUseCaseImplTest {
    private val todoListRepositoryMock = TodoListRepositoryMock()
    private val timeUtilMock = TimeUtilMock()

    private val addTodoUseCase: AddTodoUseCase = AddTodoUseCaseImpl(todoListRepositoryMock, timeUtilMock)

    @BeforeTest
    fun setup() {
        timeUtilMock.every(methodName = TimeUtilMock.Method.getTimeInMillis) { TIMESTAMP }
    }

    @Test
    fun testExecute() {
        val runs = atomic(0)
        threadedTest {
            val todo = "Play Fifa"
            val testItem = TodoItem(TIMESTAMP, todo)
            addTodoUseCase.execute(todo).test()

            timeUtilMock.verify(exactly = runs.addAndGet(1), methodName = TimeUtilMock.Method.getTimeInMillis)
            todoListRepositoryMock.verify(
                exactly = runs.value,
                methodName = TodoListRepositoryMock.Method.addTodo,
                arguments = mapOf(TodoListRepositoryMock.Arg.todoItem to testItem)
            )
        }
    }

    @Test
    fun testExecuteNoThreading() {
        val todo = "Cook amazing Pasta"
        val expectTestItem = TodoItem(TIMESTAMP, todo)

        addTodoUseCase.execute(todo).test()

        timeUtilMock.verify(methodName = TimeUtilMock.Method.getTimeInMillis)
        todoListRepositoryMock.verify(
            methodName = TodoListRepositoryMock.Method.addTodo,
            arguments = mapOf(TodoListRepositoryMock.Arg.todoItem to expectTestItem)
        )
    }

    companion object {
        private const val TIMESTAMP = 12345L
    }
}