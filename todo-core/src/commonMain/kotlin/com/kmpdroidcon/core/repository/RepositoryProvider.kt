package com.kmpdroidcon.core.repository

interface RepositoryProvider {
    fun todoListRepository(): TodoListRepository
}