package com.kmpdroidcon.todokmp.datasource.di

import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.todokmp.datasource.InMemoryTodoDataSourceImpl

object TodoInMemoryDataSourceModule {
    fun providesInMemoryTodoDataSource(): InMemoryTodoDataSource {
        return InMemoryTodoDataSourceImpl()
    }
}