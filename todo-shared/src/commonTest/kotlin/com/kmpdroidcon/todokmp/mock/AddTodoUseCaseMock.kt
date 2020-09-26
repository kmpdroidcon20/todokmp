package com.kmpdroidcon.todokmp.mock

import com.badoo.reaktive.completable.CompletableWrapper
import com.careem.mockingbird.test.Mock
import com.careem.mockingbird.test.mock
import com.kmpdroidcon.core.usecase.AddTodoUseCase

class AddTodoUseCaseMock : AddTodoUseCase, Mock {

    object Method {
        const val execute = "execute"
    }

    object Arg {
        const val todo = "todo"
    }

    override fun execute(todo: String): CompletableWrapper = mock(
        methodName = Method.execute,
        arguments = mapOf(
            Arg.todo to todo
        )
    )
}