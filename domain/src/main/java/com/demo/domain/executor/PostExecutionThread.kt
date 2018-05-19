package com.demo.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    /**
     * Make runnable operation run in the main thread.
     *
     */
    fun getScheduler(): Scheduler
}