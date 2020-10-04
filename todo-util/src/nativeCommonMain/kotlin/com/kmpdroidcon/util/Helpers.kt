package com.kmpdroidcon.util

import platform.Foundation.*
import kotlin.native.concurrent.ensureNeverFrozen
import kotlin.native.concurrent.freeze
import kotlin.native.concurrent.isFrozen


actual fun <T> T.freeze(): T = this.freeze()

actual val <T> T.isFrozen: Boolean
    get() = this.isFrozen

actual fun Any.ensureNeverFrozen() = this.ensureNeverFrozen()

actual fun getSystemTimeInMillis() = (NSDate.date().timeIntervalSince1970 * 1000.0).toLong()

actual fun Long.timeStampToDateString(): String {
    val date = NSDate.dateWithTimeIntervalSince1970(this.toDouble() / 1000)
    val dateFormatter = NSDateFormatter().apply {
        locale = NSLocale.currentLocale
        dateFormat = DateFormatConstants.DATE_FORMAT_STRING
    }
    return dateFormatter.stringFromDate(date)
}