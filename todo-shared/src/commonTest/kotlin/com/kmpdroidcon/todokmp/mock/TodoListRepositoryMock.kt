package com.kmpdroidcon.todokmp.mock

import com.careem.mockingbird.test.Mock
import com.careem.mockingbird.test.mock
import com.careem.mockingbird.test.mockUnit
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepository

/**
 * TODO this is verbose and should be fixed once MockingBird is able to generate mocks
 *
 * TODO maybe consider creating a test module and put mocks there to avoid duplication
 *
 */
class TodoListRepositoryMock : TodoListRepository, Mock {
    object Method {
        const val addTodo = "addTodo"
        const val getAll = "getAll"
    }

    object Arg {
        const val todoItem = "todoItem"
    }

    override fun addTodo(todoItem: TodoItem) = mockUnit(
        methodName = Method.addTodo,
        arguments = mapOf(Arg.todoItem to todoItem)
    )

    override fun getAll(): List<TodoItem> = mock(
        methodName = Method.getAll
    )
}