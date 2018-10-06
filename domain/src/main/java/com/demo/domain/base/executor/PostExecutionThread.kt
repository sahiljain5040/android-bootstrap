package com.demo.domain.base.executor

import io.reactivex.Scheduler

interface PostExecutionThread{

    fun getScheduler():Scheduler
}