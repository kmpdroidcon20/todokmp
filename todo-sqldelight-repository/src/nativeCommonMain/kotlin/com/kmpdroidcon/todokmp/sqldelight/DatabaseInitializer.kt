package com.kmpdroidcon.todokmp.sqldelight

import com.kmpdroidcon.todokmp.Database
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseInitializerImpl: DatabaseInitializer {
    actual override fun getDatabase(): Database {
        return Database(NativeSqliteDriver(Database.Schema, "todolist.db"))
    }
}