package com.kmpdroidcon.todokmp.data

import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.core.repository.TodoListRepository
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource
import com.kmpdroidcon.util.freeze
import kotlinx.atomicfu.AtomicBoolean
import kotlinx.atomicfu.atomic

internal class TodoListRepositoryImpl(
    private val memoryDataSource: InMemoryTodoDataSource,
    private val diskDataSource: PersistedTodoDataSource
): TodoListRepository {

    private val memoryInitialized = atomic(false)

    init {
        freeze()
    }

    override fun addTodo(todoItem: TodoItem) {
        if(memoryInitialized.value){
            memoryDataSource.addTodo(todoItem)
        }
        diskDataSource.addTodo(todoItem)
    }

    override fun getAll(): List<TodoItem> {
        loadIntoCache()
        return memoryDataSource.getAll()
    }

    private fun loadIntoCache(){
        if(!memoryInitialized.value){
            memoryDataSource.init(diskDataSource.getAll())
            memoryInitialized.value = true
        }
    }
}