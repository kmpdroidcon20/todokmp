package com.kmpdroidcon.todokmp

import com.badoo.reaktive.completable.wrap
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.badoo.reaktive.scheduler.trampolineScheduler
import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.test.base.assertSubscribed
import com.badoo.reaktive.test.completable.TestCompletable
import com.badoo.reaktive.test.completable.test
import com.badoo.reaktive.test.observable.TestObservable
import com.badoo.reaktive.test.observable.assertValues
import com.badoo.reaktive.test.observable.test
import com.careem.mockingbird.test.any
import com.careem.mockingbird.test.every
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.mock.AddTodoUseCaseMock
import com.kmpdroidcon.todokmp.mock.FetchTodosUseCaseMock
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem
import com.kmpdroidcon.util.isFrozen
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse

class TodoListViewModelImplTest {

    private val addTodoUseCaseMock = AddTodoUseCaseMock()
    private val fetchTodosUseCaseMock = FetchTodosUseCaseMock()
    private val testCompletableWrapper = TestCompletable().wrap()

    @BeforeTest
    fun setUp() {
        overrideSchedulers(
            io = { trampolineScheduler },
            main = { trampolineScheduler }
        )

        fetchTodosUseCaseMock.every(methodName = FetchTodosUseCaseMock.Method.fetch) {
            singleOf(
                TODOS
            )
        }
    }

    @Test
    fun givenTodosWhenSubscribeAfterInitThenReceiveUiItemsOnTheStream() {
        val expectedUiTodos = listOf(
            TodoUiItem(TODO_1_TIMESTAMP_EXPECTED, TODO_1_TITLE),
            TodoUiItem(TODO_2_TIMESTAMP_EXPECTED, TODO_2_TITLE)
        )
        val todoListViewModel = TodoListViewModelImpl(
            addTodoUseCase = addTodoUseCaseMock,
            fetchTodosUseCase = fetchTodosUseCaseMock
        )
        todoListViewModel.initialize()
        todoListViewModel.todoStream
            .test()
            .assertValues(expectedUiTodos)
    }

    @Test
    fun givenViewModelInitializedWhenTodoCreationThenCreationDelegatedToUseCase() {
        addTodoUseCaseMock.every(
            methodName = AddTodoUseCaseMock.Method.insert,
            arguments = mapOf(AddTodoUseCaseMock.Arg.content to any())
        ) { testCompletableWrapper }
        val todoListViewModel = TodoListViewModelImpl(
            addTodoUseCase = addTodoUseCaseMock,
            fetchTodosUseCase = fetchTodosUseCaseMock
        )
        todoListViewModel.initialize()
        todoListViewModel.createTodo(CONTENT)
        testCompletableWrapper
            .test()
            .assertSubscribed()
    }

    companion object {
        private const val CONTENT = "CONTENT"
        private const val TODO_1_TIMESTAMP = 0L
        private const val TODO_1_TIMESTAMP_EXPECTED = "0"
        private const val TODO_1_TITLE = "Todo 1"
        private const val TODO_2_TIMESTAMP = 12463874L
        private const val TODO_2_TIMESTAMP_EXPECTED = "12463874"
        private const val TODO_2_TITLE = "Todo 2"
        private val TODOS = listOf(
            TodoItem(TODO_1_TIMESTAMP, TODO_1_TITLE),
            TodoItem(TODO_2_TIMESTAMP, TODO_2_TITLE)
        )
    }
}