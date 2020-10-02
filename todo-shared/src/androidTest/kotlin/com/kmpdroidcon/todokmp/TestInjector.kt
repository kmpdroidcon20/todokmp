package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.kmpdroidcon.todokmp.dependency.PlatformDependency
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual class TestInjector {
    actual fun platformDependency(): PlatformDependency {
        return PlatformDependency(getContext())
    }

    fun getContext(): Context {
        return ApplicationProvider.getApplicationContext()
    }
}