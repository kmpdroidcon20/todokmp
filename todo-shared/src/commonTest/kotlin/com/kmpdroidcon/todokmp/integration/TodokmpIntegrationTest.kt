package com.kmpdroidcon.todokmp.integration

import com.kmpdroidcon.core.di.TodoCoreModule
import com.kmpdroidcon.todokmp.PlatformIntegrationTest
import com.kmpdroidcon.todokmp.TodoListViewModelImpl
import com.kmpdroidcon.util.isFrozen
import kotlin.test.Test
import kotlin.test.assertFalse

class TodokmpIntegrationTest: PlatformIntegrationTest() {

    @Test
    fun testFetchAllUseCase(){
        val todoListViewModel = TodoListViewModelImpl(
            addTodoUseCase = TodoCoreModule.providesAddTodoUseCase(),
            fetchTodosUseCase = TodoCoreModule.providesFetchTodosUseCase()
        )
        todoListViewModel.initialize()
        // TODO complete
        assertFalse(todoListViewModel.isFrozen)
    }
}