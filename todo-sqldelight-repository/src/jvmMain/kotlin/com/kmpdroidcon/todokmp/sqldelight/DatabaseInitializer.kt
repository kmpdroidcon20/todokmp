package com.kmpdroidcon.todokmp.sqldelight

import com.kmpdroidcon.todokmp.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual class DatabaseInitializerImpl: DatabaseInitializer {
    actual override fun getDatabase(): Database {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)
        return Database(driver)
    }
}