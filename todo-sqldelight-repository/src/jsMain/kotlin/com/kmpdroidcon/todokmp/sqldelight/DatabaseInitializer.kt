package com.kmpdroidcon.todokmp.sqldelight

import com.kmpdroidcon.todokmp.Database
import com.kmpdroidcon.todokmp.TodoItemModelQueries
import com.squareup.sqldelight.TransactionWithReturn
import com.squareup.sqldelight.TransactionWithoutReturn

actual class DatabaseInitializerImpl: DatabaseInitializer {
    actual override fun getDatabase(): Database {
        return JsDataBase() // Don't worry no one will call this till we have the SqlDelight artifact :D
    }
}

class JsDataBase : Database {
    override val todoItemModelQueries: TodoItemModelQueries
        get() = TODO("Not yet implemented")

    override fun transaction(noEnclosing: Boolean, body: TransactionWithoutReturn.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun <R> transactionWithResult(
        noEnclosing: Boolean,
        bodyWithReturn: TransactionWithReturn<R>.() -> R
    ): R {
        TODO("Not yet implemented")
    }
}