package com.kmpdroidcon.todokmp.mock

import com.careem.mockingbird.test.Mock
import com.careem.mockingbird.test.mock
import com.kmpdroidcon.util.time.TimeUtil


/**
 * TODO this is verbose and should be fixed once MockingBird is able to generate mocks
 *
 * TODO maybe consider creating a test module and put mocks there to avoid duplication
 *
 */
class TimeUtilMock : TimeUtil, Mock {
    object Method {
        const val getTimeInMillis = "getTimeInMillis"
    }

    override fun getTimeInMillis(): Long = mock(
        methodName = Method.getTimeInMillis
    )
}