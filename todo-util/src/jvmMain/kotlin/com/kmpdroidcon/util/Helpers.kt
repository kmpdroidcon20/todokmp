package com.kmpdroidcon.util

actual fun <T> T.freeze(): T = this

actual val <T> T.isFrozen: Boolean
    get() = false

actual fun Any.ensureNeverFrozen() {}

actual fun getSystemTimeInMillis() = System.currentTimeMillis()