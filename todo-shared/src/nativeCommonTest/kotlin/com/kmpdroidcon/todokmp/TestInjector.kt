package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
import com.kmpdroidcon.todokmp.Database
import com.kmpdroidcon.todokmp.dependency.PlatformDependency
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class TestInjector {
    actual fun platformDependency(): PlatformDependency{
        return PlatformDependency()
    }
}