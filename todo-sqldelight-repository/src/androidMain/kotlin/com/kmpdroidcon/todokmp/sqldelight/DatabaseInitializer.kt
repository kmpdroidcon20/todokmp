package com.kmpdroidcon.todokmp.sqldelight

import android.content.Context
import com.kmpdroidcon.todokmp.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual class DatabaseInitializerImpl(private val context: Context): DatabaseInitializer {
    actual override fun getDatabase(): Database {
        return Database(
            AndroidSqliteDriver(
                Database.Schema,
                context,
                "todolist.db"
            )
        )
    }
}