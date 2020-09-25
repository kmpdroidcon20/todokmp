package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.single.SingleWrapper
import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.single.wrap
import com.kmpdroidcon.core.model.TodoItem

internal class FetchTodosUseCaseImpl : FetchTodosUseCase {
    override fun fetch(): SingleWrapper<List<TodoItem>> {
        // TODO fetch from repo here
        return singleOf(
            listOf(
                TodoItem(0, "Todo 1"),
                TodoItem(12463874, "Todo 2")
            )
        ).wrap()
    }
}