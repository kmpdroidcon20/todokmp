package com.kmpdroidcon.todokmp

import com.kmpdroidcon.todokmp.dependency.PlatformDependency

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
expect class TestInjector {
    fun getTodoDatabase(): Database
    fun platformDependency(): PlatformDependency
}