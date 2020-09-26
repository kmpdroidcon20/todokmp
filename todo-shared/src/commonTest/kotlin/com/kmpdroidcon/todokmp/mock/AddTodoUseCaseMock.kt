package com.kmpdroidcon.todokmp.mock

import com.badoo.reaktive.completable.CompletableWrapper
import com.careem.mockingbird.test.Mock
import com.careem.mockingbird.test.mock
import com.kmpdroidcon.core.usecase.AddTodoUseCase

class AddTodoUseCaseMock : AddTodoUseCase, Mock {

    object Method {
        const val insert = "insert"
    }

    object Arg {
        const val content = "content"
    }

    override fun insert(content: String): CompletableWrapper = mock(
        methodName = Method.insert,
        arguments = mapOf(
            Arg.content to content
        )
    )
}