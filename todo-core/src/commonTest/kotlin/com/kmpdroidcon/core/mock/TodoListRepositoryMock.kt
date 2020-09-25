package com.kmpdroidcon.core.mock

import com.careem.mockingbird.test.Mock
import com.careem.mockingbird.test.mock
import com.careem.mockingbird.test.mockUnit
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepository

class TodoListRepositoryMock : TodoListRepository, Mock {
    object Method {
        const val addTodo = "addTodo"
        const val getAll = "getAll"
        const val count = "count"
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

    override fun count(): Int = mock(
        methodName = Method.count
    )
}