package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */

actual abstract class PlatformIntegrationTest {
    private val testInjector = TestInjector()
    actual fun testInjector(): TestInjector = testInjector
}