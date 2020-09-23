package com.kmpdroidcon.todokmp

/**
 * Created by
 * Marco Signoretto
 * Android Developer
 * on 23/09/2020.
 */
import android.provider.Settings
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config
actual abstract class PlatformIntegrationTest {
    private val testInjector = TestInjector()
    actual fun testInjector(): TestInjector = testInjector
    actual open fun setup() {
        Settings.Secure.putString(
            testInjector.getContext().contentResolver,
            Settings.Secure.ANDROID_ID,
            "test_id"
        )
    }
}