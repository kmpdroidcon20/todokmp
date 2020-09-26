package com.kmpdroidcon.todokmp.integration

import com.kmpdroidcon.todokmp.PlatformIntegrationTest
import com.kmpdroidcon.todokmp.data.dependency.InMemoryTodoDataSource
import com.kmpdroidcon.todokmp.data.dependency.PersistedTodoDataSource
import com.kmpdroidcon.todokmp.datasource.di.TodoInMemoryDataSourceModule
import com.kmpdroidcon.todokmp.di.DIGraph
import com.kmpdroidcon.todokmp.sqldelight.dao.TodoItemDao
import com.kmpdroidcon.todokmp.sqldelight.di.SqlDelightTodoDataSourceModule
import com.kmpdroidcon.util.isFrozen
import kotlin.test.Test
import kotlin.test.assertFalse

class TodokmpIntegrationTest : PlatformIntegrationTest() {

    @Test
    fun testFetchAllUseCase() {
        val todoListViewModel = diGraph().build(testInjector().platformDependency())
        // TODO complete
        assertFalse(todoListViewModel.isFrozen)
    }

    private fun diGraph(): DIGraph {
        return object : DIGraph() {
            override fun memoryDataSource(): InMemoryTodoDataSource {
                return TodoInMemoryDataSourceModule.providesInMemoryTodoDataSource() // TODO add Spy here
            }

            override fun buildPersistedTodoDataSource(todoItemDao: TodoItemDao): PersistedTodoDataSource {
                return SqlDelightTodoDataSourceModule.providesPersistedTodoDataSource(todoItemDao) // TODO add Spy here
            }
        }
    }
}