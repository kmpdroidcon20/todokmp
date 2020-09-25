package com.kmpdroidcon.todokmp

import com.kmpdroidcon.core.di.TodoCoreModule
import com.kmpdroidcon.util.isFrozen
import kotlin.test.Test
import kotlin.test.assertFalse

class TodoListViewModelImplTest {
    @Test
    fun testDoNotFreezeOnInit() {
        val todoListViewModel = TodoListViewModelImpl(
            addTodoUseCase = TodoCoreModule.providesAddTodoUseCase(),
            fetchTodosUseCase = TodoCoreModule.providesFetchTodosUseCase()
        )
        todoListViewModel.initialize()
        assertFalse(todoListViewModel.isFrozen)
    }

//    @Test
//    fun testDoNotFreezeOnStreamSubscription() {
//        val todoListViewModel = TodoListViewModelImpl(
//            addTodoUseCase = TodoCoreModule.providesAddTodoUseCase(),
//            fetchTodosUseCase = TodoCoreModule.providesFetchTodosUseCase()
//        )
//        todoListViewModel.initialize()
//        // TODO complete
//        assertFalse(todoListViewModel.isFrozen)
//    }
//
//    @Test
//    fun testDoNotFreezeOnStreamEmission() {
//        val todoListViewModel = TodoListViewModelImpl(
//            addTodoUseCase = TodoCoreModule.providesAddTodoUseCase(),
//            fetchTodosUseCase = TodoCoreModule.providesFetchTodosUseCase()
//        )
//        todoListViewModel.initialize()
//        // TODO complete
//        assertFalse(todoListViewModel.isFrozen)
//    }
}