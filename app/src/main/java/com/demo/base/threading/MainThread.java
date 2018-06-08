package com.demo.base.threading;

import android.os.Handler;
import android.os.Looper;

import com.demo.domain.base.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
@Singleton
public class MainThread implements PostExecutionThread {

    private Handler mHandler;

    @Inject
    public MainThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
