package com.kmpdroidcon.todokmp

import com.badoo.reaktive.observable.ObservableWrapper
import com.badoo.reaktive.observable.observableOf
import com.badoo.reaktive.observable.wrap
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem

class TodoListViewModelImpl : TodoListViewModel {
    override val todoStream: ObservableWrapper<List<TodoUiItem>>
        get() {
            // TODO use repository here
            return observableOf(
                listOf(
                    TodoUiItem("12/12/2020 12:40", "Todo 1"),
                    TodoUiItem("12/12/2020 12:50", "Todo 2")
                )
            ).wrap()
        }

    override fun createTodo(content: String) {
        TODO("Not yet implemented")
    }
}