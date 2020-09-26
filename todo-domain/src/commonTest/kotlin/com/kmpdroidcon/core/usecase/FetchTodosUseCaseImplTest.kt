package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.test.single.test
import com.careem.mockingbird.test.every
import com.careem.mockingbird.test.verify
import com.kmpdroidcon.core.mock.TodoListRepositoryMock
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.util.threadedTest
import kotlinx.atomicfu.atomic
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchTodosUseCaseImplTest {
    private val todoListRepositoryMock = TodoListRepositoryMock()
    private val fetchTodosUseCaseImpl = FetchTodosUseCaseImpl(todoListRepositoryMock)

    @BeforeTest
    fun setup() {
        todoListRepositoryMock.every(
            methodName = TodoListRepositoryMock.Method.getAll
        ) { TODO_LIST }
    }

    @Test
    fun execute() {
        val runs = atomic(0)
        threadedTest {
            val testObserver = fetchTodosUseCaseImpl.execute().test()

            todoListRepositoryMock.verify(
                exactly = runs.addAndGet(1),
                methodName = TodoListRepositoryMock.Method.getAll
            )
            assertEquals(TODO_LIST, testObserver.value)
        }
    }

    companion object {
        private val TODO_LIST = listOf(
            TodoItem(12345L, "todo1"),
            TodoItem(54321L, "todo2")
        )
    }
}