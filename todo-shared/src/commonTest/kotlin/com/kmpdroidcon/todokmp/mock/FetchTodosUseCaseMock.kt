package com.kmpdroidcon.todokmp.mock

import com.badoo.reaktive.single.SingleWrapper
import com.careem.mockingbird.test.Mock
import com.careem.mockingbird.test.mock
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.usecase.FetchTodosUseCase

class FetchTodosUseCaseMock : FetchTodosUseCase, Mock {

    object Method {
        const val execute = "execute"
    }

    override fun execute(): SingleWrapper<List<TodoItem>> = mock(
        methodName = Method.execute
    )
}