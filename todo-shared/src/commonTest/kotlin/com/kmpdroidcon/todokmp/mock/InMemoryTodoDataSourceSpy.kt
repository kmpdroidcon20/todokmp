package com.kmpdroidcon.todokmp.mock

import com.careem.mockingbird.test.Spy
import com.careem.mockingbird.test.spy
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource

class InMemoryTodoDataSourceSpy(
    private val delegate: InMemoryTodoDataSource
) : InMemoryTodoDataSource, Spy {

    object Method {
        const val addTodo = "addTodo"
        const val getAll = "getAll"
        const val init = "init"
    }

    object Arg {
        const val todoItem = "todoItem"
        const val todos = "todos"
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

    override fun init(todos: List<TodoItem>) = spy(
        methodName = Method.init,
        arguments = mapOf(
            Arg.todos to todos
        ),
        delegate = { delegate.init(todos) }
    )
}