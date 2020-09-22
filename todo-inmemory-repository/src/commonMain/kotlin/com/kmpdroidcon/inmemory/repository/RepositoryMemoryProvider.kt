package com.kmpdroidcon.inmemory.repository

import com.kmpdroidcon.core.repository.RepositoryProvider
import com.kmpdroidcon.core.repository.TodoListRepository

class RepositoryMemoryProvider : RepositoryProvider {
    override fun todoListRepository(): TodoListRepository = TodoListMemoryRepository()
}