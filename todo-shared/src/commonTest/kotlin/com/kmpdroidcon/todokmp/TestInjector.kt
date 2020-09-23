package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */

import com.kmpdroidcon.todokmp.Database

expect class TestInjector {
    fun getTodoDatabase(): Database
}