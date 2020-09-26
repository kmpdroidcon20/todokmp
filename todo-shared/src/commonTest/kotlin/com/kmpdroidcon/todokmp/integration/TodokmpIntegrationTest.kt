package com.kmpdroidcon.todokmp.integration

import com.kmpdroidcon.todokmp.PlatformIntegrationTest
import com.kmpdroidcon.todokmp.di.TodoSharedModule
import com.kmpdroidcon.util.isFrozen
import kotlin.test.Test
import kotlin.test.assertFalse

class TodokmpIntegrationTest : PlatformIntegrationTest() {

    @Test
    fun testFetchAllUseCase(){
        val todoListViewModel = TodoSharedModule.providesTodoListViewModel(testInjector().platformDependency())
        // TODO complete
        assertFalse(todoListViewModel.isFrozen)
    }
}