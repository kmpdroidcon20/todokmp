package com.kmpdroidcon.util

import com.careem.mockingbird.test.runOnWorker

// TODO Move test module
fun threadedTest(body: () -> Unit) {
    body()
    runOnWorker(body)
}