package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config
actual abstract class PlatformIntegrationTest {
    private val testInjector = TestInjector()
    actual fun testInjector(): TestInjector = testInjector
}