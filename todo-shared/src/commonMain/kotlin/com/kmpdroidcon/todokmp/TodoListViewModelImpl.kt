package com.kmpdroidcon.todokmp

import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.completable.subscribe
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.observable.ObservableWrapper
import com.badoo.reaktive.observable.wrap
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.single.subscribeOn
import com.badoo.reaktive.subject.behavior.BehaviorSubject
import com.kmpdroidcon.core.usecase.AddTodoUseCase
import com.kmpdroidcon.core.usecase.FetchTodosUseCase
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem
import com.kmpdroidcon.util.ensureNeverFrozen
import com.kmpdroidcon.util.isFrozen

internal class TodoListViewModelImpl(
    private val addTodoUseCase: AddTodoUseCase,
    private val fetchTodosUseCase: FetchTodosUseCase
) : TodoListViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var _todoStream: ObservableWrapper<List<TodoUiItem>>

    init {
        ensureNeverFrozen()
    }

    override fun initialize() {
        val todoSubject = BehaviorSubject<List<TodoUiItem>>(emptyList())
        _todoStream = todoSubject.wrap()
        val disposable = fetchTodosUseCase.fetch()
            .subscribeOn(ioScheduler)
            .map {
                val uiTodos = it.map { todoItem ->
                    TodoUiItem(
                        todoItem.timestamp.toString(), // TODO fix this
                        todoItem.todo
                    )
                }
                uiTodos
            }
            .observeOn(mainScheduler)
            .subscribe {
                todoSubject.onNext(it)
            }
        compositeDisposable.add(disposable)
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override val todoStream: ObservableWrapper<List<TodoUiItem>>
        get() {
            return _todoStream
        }

    override fun createTodo(content: String) {
        addTodoUseCase.insert(content)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe()
    }
}