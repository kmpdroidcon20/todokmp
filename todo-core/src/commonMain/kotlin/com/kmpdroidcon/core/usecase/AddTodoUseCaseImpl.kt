package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.completable.CompletableWrapper
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.completable.wrap
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.util.time.TimeUtil

internal class AddTodoUseCaseImpl(
    private val todoListRepository: TodoListRepository,
    private val timeUtil: TimeUtil
) : AddTodoUseCase {
    override fun execute(todo: String): CompletableWrapper = completableFromFunction {
        val item = TodoItem(
            timestamp = timeUtil.getTimeInMillis(),
            todo = todo
        )
        todoListRepository.addTodo(item)
    }.wrap()
}