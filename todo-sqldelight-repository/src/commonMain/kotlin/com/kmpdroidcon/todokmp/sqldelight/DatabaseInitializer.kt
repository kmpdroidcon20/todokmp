package com.kmpdroidcon.todokmp.sqldelight

import com.kmpdroidcon.todokmp.Database

interface DatabaseInitializer {
    fun getDatabase(): Database
}

expect class DatabaseInitializerImpl : DatabaseInitializer {
    override fun getDatabase(): Database
}