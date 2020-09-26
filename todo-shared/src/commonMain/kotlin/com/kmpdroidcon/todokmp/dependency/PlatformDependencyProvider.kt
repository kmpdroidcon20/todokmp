package com.kmpdroidcon.todokmp.dependency

import com.kmpdroidcon.todokmp.sqldelight.DatabaseInitializer

internal expect class PlatformDependencyProvider(platformDependency: PlatformDependency) {
    fun provideDatabaseInitializer(): DatabaseInitializer
}