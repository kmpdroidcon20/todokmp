package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.single.SingleWrapper
import com.kmpdroidcon.core.model.TodoItem

/**
 * Usecase that will provide all the Todo items
 */
interface FetchTodosUseCase {
    fun fetch(): SingleWrapper<List<TodoItem>>
}