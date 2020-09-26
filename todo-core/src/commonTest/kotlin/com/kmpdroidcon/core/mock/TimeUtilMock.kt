package com.kmpdroidcon.core.mock

import com.careem.mockingbird.test.Mock
import com.careem.mockingbird.test.mock
import com.kmpdroidcon.util.time.TimeUtil

class TimeUtilMock : TimeUtil, Mock {
    object Method {
        const val getTimeInMillis = "getTimeInMillis"
    }

    override fun getTimeInMillis(): Long = mock(
        methodName = Method.getTimeInMillis
    )
}