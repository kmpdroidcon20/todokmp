package com.kmpdroidcon.util

import kotlinx.datetime.internal.JSJoda.Instant
import kotlinx.datetime.internal.JSJoda.DateTimeFormatter
import kotlinx.datetime.internal.JSJoda.LocalDateTime
import kotlin.js.Date

actual fun getSystemTimeInMillis() = Date.now().toLong()

actual fun Long.timeStampToDateString(): String {
    val date: Instant = Instant.ofEpochMilli(this)
    val formatter = DateTimeFormatter.ofPattern(DateFormatConstants.DATE_FORMAT_STRING)
    return LocalDateTime.ofInstant(date).format(formatter)
}

@JsModule("@js-joda/timezone")
@JsNonModule
internal external object JsJodaTimeZoneModule

private val jsJodaTz = JsJodaTimeZoneModule