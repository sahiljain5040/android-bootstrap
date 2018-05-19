package com.demo.domain.executor.impl

import com.demo.domain.executor.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadExecutor:Executor {

    private val CORE_POOL_SIZE = 3
    private val MAX_POOL_SIZE = 5
    private val KEEP_ALIVE_TIME = 120
    private val TIME_UNIT = TimeUnit.SECONDS
    private val WORK_QUEUE = LinkedBlockingQueue<Runnable>()
    private val mThreadPoolExecutor: ThreadPoolExecutor

    init {
        val keepAlive = KEEP_ALIVE_TIME.toLong()
        mThreadPoolExecutor = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE)
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor.execute(runnable)
    }
}