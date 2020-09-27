package com.kmpdroidcon.todokmp.mock

import com.careem.mockingbird.test.Spy
import com.careem.mockingbird.test.spy
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource

class PersistedTodoDataSourceSpy(private val delegate: PersistedTodoDataSource) :
    PersistedTodoDataSource, Spy {

    object Method {
        const val addTodo = "addTodo"
        const val getAll = "getAll"
    }

    object Arg {
        const val todoItem = "todoItem"
    }

    override fun addTodo(todoItem: TodoItem) = spy(
        methodName = Method.addTodo,
        arguments = mapOf(
            Arg.todoItem to todoItem
        ),
        delegate = { delegate.addTodo(todoItem) }
    )

    override fun getAll(): List<TodoItem> = spy(
        methodName = Method.getAll,
        delegate = { delegate.getAll() }
    )
}