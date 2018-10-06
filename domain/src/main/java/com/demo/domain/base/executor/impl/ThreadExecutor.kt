package com.demo.domain.base.executor.impl

import com.demo.domain.base.executor.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ThreadExecutor @Inject constructor():Executor{

    private val CORE_POOL_SIZE = 3
    private val MAX_POOL_SIZE = 5
    private val KEEP_ALIVE_TIME: Long = 120
    private val TIME_UNIT = TimeUnit.SECONDS
    private val WORK_QUEUE = LinkedBlockingQueue<Runnable>()

    private var mThreadPoolExecutor: ThreadPoolExecutor

    init {
        mThreadPoolExecutor = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                WORK_QUEUE)
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor.execute(runnable)
    }

}