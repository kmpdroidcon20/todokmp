package com.kmpdroidcon.todokmp.dependency

import com.kmpdroidcon.todokmp.sqldelight.DatabaseInitializer
import com.kmpdroidcon.todokmp.sqldelight.DatabaseInitializerImpl

actual class PlatformDependencyProvider actual constructor(
    private val platformDependency: PlatformDependency
) {
    actual fun provideDatabaseInitializer(): DatabaseInitializer {
        return DatabaseInitializerImpl()
    }
}