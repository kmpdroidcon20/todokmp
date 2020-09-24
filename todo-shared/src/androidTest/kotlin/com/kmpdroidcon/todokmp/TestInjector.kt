package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
import android.content.Context
import com.kmpdroidcon.todokmp.Database
import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual class TestInjector {
    actual fun getTodoDatabase(): Database{
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
            Database.Schema.create(this)
        }
        return Database(driver)
    }

    fun getContext(): Context {
        return ApplicationProvider.getApplicationContext()
    }
}