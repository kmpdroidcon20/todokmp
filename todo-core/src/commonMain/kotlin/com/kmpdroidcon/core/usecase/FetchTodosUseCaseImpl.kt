package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.single.SingleWrapper
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.wrap
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepository

internal class FetchTodosUseCaseImpl(
    private val todoListRepository: TodoListRepository
) : FetchTodosUseCase {
    override fun execute(): SingleWrapper<List<TodoItem>> = singleFromFunction {
        todoListRepository.getAll()
    }.wrap()
}