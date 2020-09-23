package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
import com.kmpdroidcon.todokmp.Database

class TestInjector {
    fun getTodoDatabase(): Database {
        val driver = NativeSqliteDriver(Database.Schema, "integration_test.db")
        return Database(driver)
    }
}