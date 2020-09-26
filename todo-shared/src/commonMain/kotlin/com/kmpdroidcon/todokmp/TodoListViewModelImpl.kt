package com.kmpdroidcon.todokmp

import com.badoo.reaktive.completable.andThen
import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.observable.ObservableWrapper
import com.badoo.reaktive.observable.wrap
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import com.badoo.reaktive.subject.behavior.BehaviorSubject
import com.kmpdroidcon.core.usecase.AddTodoUseCase
import com.kmpdroidcon.core.usecase.FetchTodosUseCase
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem
import com.kmpdroidcon.util.ensureNeverFrozen
import kotlinx.atomicfu.atomic

internal class TodoListViewModelImpl(
    private val addTodoUseCase: AddTodoUseCase,
    private val fetchTodosUseCase: FetchTodosUseCase
) : TodoListViewModel {

    private val todoSubject = BehaviorSubject<List<TodoUiItem>>(emptyList())

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        ensureNeverFrozen()
    }

    override fun initialize() {
        postState(
            refresh()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        )
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override val todoStream: ObservableWrapper<List<TodoUiItem>>
        get() {
            return todoSubject.wrap()
        }

    override fun createTodo(content: String) {
        postState(
            addTodoUseCase.execute(content)
                .andThen(
                    refresh()
                )
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        )
    }

    private fun refresh() = fetchTodosUseCase.execute()
        .map {
            val uiTodos = it.map { todoItem ->
                TodoUiItem(
                    todoItem.timestamp.toString(), // TODO fix this
                    todoItem.todo
                )
            }
            uiTodos
        }

    private fun postState(single: Single<List<TodoUiItem>>) {
        val todoSubjectRef = atomic(todoSubject)
        compositeDisposable.add(single
            .subscribe { uiTodos ->
                todoSubjectRef.value.onNext(uiTodos)
            })
    }

}