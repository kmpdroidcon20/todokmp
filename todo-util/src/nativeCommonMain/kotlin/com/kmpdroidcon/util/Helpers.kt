package com.kmpdroidcon.util

import kotlin.native.concurrent.ensureNeverFrozen
import kotlin.native.concurrent.freeze
import kotlin.native.concurrent.isFrozen
import platform.Foundation.NSDate
import platform.Foundation.NSUUID
import platform.Foundation.date
import platform.Foundation.timeIntervalSince1970


actual fun <T> T.freeze(): T = this.freeze()

actual val <T> T.isFrozen: Boolean
    get() = this.isFrozen

actual fun Any.ensureNeverFrozen() = this.ensureNeverFrozen()

actual fun getSystemTimeInMillis() = (NSDate.date().timeIntervalSince1970 * 1000.0).toLong()