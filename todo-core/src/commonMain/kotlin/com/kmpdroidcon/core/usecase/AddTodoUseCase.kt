package com.kmpdroidcon.core.usecase

import com.badoo.reaktive.completable.CompletableWrapper

/**
 * Usecase to insert a new todo
 */
interface AddTodoUseCase {
    fun insert(content: String): CompletableWrapper
}