package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
expect abstract class PlatformIntegrationTest() {
    fun testInjector(): TestInjector
}