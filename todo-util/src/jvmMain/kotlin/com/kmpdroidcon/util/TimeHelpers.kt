package com.kmpdroidcon.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

actual fun getSystemTimeInMillis() = System.currentTimeMillis()

actual fun Long.timeStampToDateString(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return DateFormatHelper.DATE_FORMAT.format(calendar.time)
}

object DateFormatHelper {
    @Suppress("SimpleDateFormat")
    internal val DATE_FORMAT: DateFormat =
        SimpleDateFormat(DateFormatConstants.DATE_FORMAT_STRING)
}