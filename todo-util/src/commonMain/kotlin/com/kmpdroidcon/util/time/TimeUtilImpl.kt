package com.kmpdroidcon.util.time

import com.kmpdroidcon.util.getSystemTimeInMillis

class TimeUtilImpl : TimeUtil {
    override fun getTimeInMillis() = getSystemTimeInMillis()
}